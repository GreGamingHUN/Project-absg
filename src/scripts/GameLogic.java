package scripts;

import com.sun.jdi.InvalidTypeException;
import jdk.swing.interop.SwingInterOpUtils;
import magics.Fireball;
import magics.Lightning;
import magics.Revive;
import units.*;

import java.io.IOException;
import java.util.*;


public class GameLogic {
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BOLD = "\u001B[1m";

    //Game States
    //0 = buying phase
    //1 = fight phase

    int gameState = 0;
    int gameEnded = 0;


    Hos player;
    Hos enemy;

    static public int gridSizeX = 10;
    static public int gridSizeY = 12;
    static public Tile[][] tileArray = new Tile[gridSizeX][gridSizeY];
    final int difficulty;

    public GameLogic(int difficulty) {
        this.difficulty = difficulty;
        player = new Hos(difficulty);
    }

    public void startGame() throws IOException, InterruptedException {
        setUpGrid();

        while (gameState == 0) {
            preparePhase();
        }
        while (gameState == 1) {
            placementPhase();
        }
        while (gameState == 2) {
            overviewPhase();
        }
        fightPhase();
    }

    public void setUpGrid() {
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                tileArray[i][j] = new Tile(i, j);
            }
        }
        System.out.println("Game created");
    }
    public void  drawGrid() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        for (int i = 0; i < gridSizeX; i++) {
            if (i == 0) {
                System.out.println("     0   1   2   3   4   5   6   7   8   9   10  11");
            }
            if (i < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
            System.out.print(TEXT_GREEN + "|" + TEXT_RESET);
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() != null) {
                    if (tileArray[i][j].getEmberOnTile().getParentHos() == enemy) {
                        if (tileArray[i][j].getEmberOnTile().isSelected()) {
                            System.out.print(" " + TEXT_YELLOW + tileArray[i][j].getEmberOnTile().getUnitShort() + TEXT_RESET + " ");
                        } else {
                            System.out.print(" " + TEXT_RED + tileArray[i][j].getEmberOnTile().getUnitShort() + " " + TEXT_RESET);
                        }
                    } else {
                        if (tileArray[i][j].getEmberOnTile().isSelected() && tileArray[i][j].getEmberOnTile().getParentHos() == player) {
                            System.out.print(" " + TEXT_GREEN + tileArray[i][j].getEmberOnTile().getUnitShort() + TEXT_RESET + " ");
                        }else {
                            System.out.print(" " + tileArray[i][j].getEmberOnTile().getUnitShort() + " ");
                        }
                    }
                } else {
                    if (tileArray[i][j].isCanGohere()) {
                        System.out.print(" + ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.print(TEXT_GREEN + "|" + TEXT_RESET);
            }
            System.out.println("");
        }
    }
    public void preparePhase() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("Penzed = " + player.getBalance() + "\n");
        System.out.println("Mit szeretnel tenni?");
        System.out.println("1 - Egysegek vasarlasa");
        System.out.println("2 - Kepessegek fejlesztese");
        System.out.println("3 - Varazslatok vasarlasa\n");
        System.out.println("4 - Megvasarolt egysegek mutatasa\n");
        System.out.println("5 - Start");
        System.out.println("6 - Kilepes\n\n");
        System.out.println("Tulajdonsagok:               Varazslatok");


        System.out.println("Tamadas:  \t\t" + player.getDmgUp()+     "Villam:          " + (player.isBoughtLightning() ? "Vasarolva" : "Nincs vasarolva"));
        System.out.println("Vedekezes:\t\t" + player.getDefUp()+     "Tuzlabda:        " + (player.isBoughtFireball() ? "Vasarolva" : "Nincs vasarolva"));
        System.out.println("Varazsero:\t\t" + player.getMagicUp()+   "Feltamasztas:    " + (player.isBoughtRevive() ? "Vasarolva" : "Nincs vasarolva"));
        System.out.println("Tudas:    \t\t" + player.getKnowledge());
        System.out.println("Moral:    \t\t" + player.getMoral());
        System.out.println("Szerencse:\t\t" + player.getLuck());
        Scanner sc = new Scanner(System.in);
        System.out.print("\nOpcio: ");
        int number;

        try {
            number = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ervenytelen input!");
            return;
        }

        switch (number) {
            case 1 -> buyUnit();
            case 2 -> buyAbility();
            case 3 -> buyMagic();
            case 4 -> {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                System.out.println("Egyseg neve        Egyseg merete");
                for (int i = 0; i < player.getBoughtUnits().length; i++) {
                    if (player.getBoughtUnits()[i] != null) {
                        System.out.println(player.getBoughtUnits()[i].getUnitName() + "             " + player.getBoughtUnits()[i].getUnitAmount());
                    }
                }
                Scanner scanner = new Scanner(System.in);
                System.out.println("\n1 - Vissza");
                int result;

                try {
                    result = scanner.nextInt();
                    if (result != 1) {
                        System.out.println("Ervenytelen input!");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Ervenytelen input!");
                }
            }
            case 5 -> {
                gameState = 1;
            }
            case 6 -> {
                System.out.println("Biztos ki szeretnel lepni a jatekbol? (y/n)");
                if (Objects.equals(sc.next(), "y") || Objects.equals(sc.next(), "Y")) {
                    System.exit(0);
                }
            }
        }
    }
    public void placementPhase() throws IOException, InterruptedException {
        for (int i = 0; i < player.getBoughtUnits().length; i++) {
            drawGrid();
            if (player.getBoughtUnits()[i] != null) {
                System.out.println("\nHelyezd el ezt az egyseget a palyan: " +
                        player.getBoughtUnits()[i].getUnitName() + "    " + player.getBoughtUnits()[i].getUnitAmount());

                Scanner sc = new Scanner(System.in);
                int posX = -1, posY = -1;
                while (posY > 1 || posY < 0) {
                    try {
                        System.out.print("\nX pozicio (0-1):");
                        posY = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Ervenytelen input!");
                        sc.nextLine();
                        Thread.sleep(1000);
                    }
                }
                while (posX > 9 || posX < 0) {
                    try {
                        System.out.println("Y pozicio (0-9):");
                        posX = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Ervenytelen input!");
                        sc.nextLine();
                        Thread.sleep(1000);
                    }
                }

                if (tileArray[posX][posY].getEmberOnTile() != null) {
                    System.out.println("A hely foglalt!");
                    i--;
                    try
                    {
                        System.in.read();
                    }
                    catch(Exception e)
                    {}
                } else {
                    player.getBoughtUnits()[i].setPosX(posX);
                    player.getBoughtUnits()[i].setPosY(posY);

                    tileArray[player.getBoughtUnits()[i].getPosX()][player.getBoughtUnits()[i].getPosY()].setEmberOnTile(player.getBoughtUnits()[i]);
                }

            }
        }

        System.out.println("\nMit szeretnel tenni?");
        System.out.println("1 - Tovabb");
        System.out.println("2 - Kilepes");
        System.out.print("Opcio: ");
        Scanner sc = new Scanner(System.in);

        switch (sc.nextInt()) {
            case 1:
                gameState = 2;
                break;
            case 2:
                if (sc.nextInt() == 2) {
                    System.out.println("Biztos ki szeretnel lepni a jatekbol? (y/n)");
                    if (sc.next().equals("y") || sc.next().equals("Y")) {
                        System.exit(0);
                    }
                }
                break;
        }

    }
    public void overviewPhase() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        enemy = new Hos();
        createEnemy();
        while (true) {
            drawGrid();
            for (int i = 0; i < player.getBoughtUnits().length; i++) {
                if (player.getBoughtUnits()[i] != null) {
                    player.getBoughtUnits()[i].setPriority(player.getBoughtUnits()[i].getPriority() + player.getMoral() - 1);
                }
            }
            for (int i = 0; i < player.getBoughtUnits().length - 1; i++) {
                for (int j = 0; j < player.getBoughtUnits().length - 1 - i; j++) {
                    if (player.getBoughtUnits()[j] != null &&player.getBoughtUnits()[j + 1] != null) {
                        if (player.getBoughtUnits()[j].getPriority() < player.getBoughtUnits()[j+1].getPriority()) {
                            Unit swap = player.getBoughtUnits()[j];
                            player.getBoughtUnits()[j] = player.getBoughtUnits()[j+1];
                            player.getBoughtUnits()[j+1] = swap;
                        }
                    }

                }
            }
            System.out.println("Egysegeid sorrendben:");
            for (int i = 0; i < player.getBoughtUnits().length; i++) {
                if (player.getBoughtUnits()[i] != null) {
                    System.out.println(player.getBoughtUnits()[i].getUnitName() + "     " + player.getBoughtUnits()[i].getPriority());
                }
            }

            System.out.println("\n\n\n\n1 - Ellenfel adatainak megtekintese");
            System.out.println("2 - Start");
            try {
                switch (sc.nextInt()) {
                    case 1:
                        overviewEnemy();
                        break;
                    case 2:
                        gameState = 3;
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ervenytelen input!");
            }
        }
    }
    public void overviewEnemy() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            drawGrid();
            System.out.println("Ellenfel tulajdonsagai:");
            System.out.println("Tamadas:  \t\t" + enemy.getDmgUp()+     "\t\tVillam:          " + (enemy.isBoughtLightning() ? "Vasarolva" : "Nincs vasarolva"));
            System.out.println("Vedekezes:\t\t" + enemy.getDefUp()+     "\t\tTuzlabda:        " + (enemy.isBoughtFireball() ? "Vasarolva" : "Nincs vasarolva"));
            System.out.println("Varazsero:\t\t" + enemy.getMagicUp()+   "\t\tFeltamasztas:    " + (enemy.isBoughtRevive() ? "Vasarolva" : "Nincs vasarolva"));
            System.out.println("Tudas:    \t\t" + enemy.getKnowledge());
            System.out.println("Moral:    \t\t" + enemy.getMoral());
            System.out.println("Szerencse:\t\t" + enemy.getLuck());
            System.out.println("1 - Vissza");
            try {
                if (sc.nextInt() == 1) {
                    return;
                } else {
                    System.out.println("Ervenytelen input!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ervenytelen input!");
            }
        }
    }
    public void fightPhase() throws IOException, InterruptedException {
        player.setMana(player.getKnowledge() * 10);
        int round = 1;
        while (gameEnded == 0) {
            player.setUsedAttackMagic(false);
            roundPlayer(round);
            roundEnemy();
            clearVisszatamadott();
            for (int i = 0; i < gridSizeX; i++) {
                for (int j = 0; j < gridSizeY; j++) {
                    if (tileArray[i][j].getEmberOnTile() != null && tileArray[i][j].getEmberOnTile().getHp() <= 0) {
                        tileArray[i][j].setEmberOnTile(null);
                    }
                }
            }
            for (int i = 0; i < player.getBoughtUnits().length; i++) {
                if (player.getBoughtUnits()[i] != null && player.getBoughtUnits()[i].getHp() <= 0) {
                    player.getBoughtUnits()[i] = null;
                }
            }
            boolean playerHasUnits = false;
            for (int i = 0; i < player.getBoughtUnits().length; i++) {
                if (player.getBoughtUnits()[i] != null) {
                    playerHasUnits = true;
                }
            }

            for (int i = 0; i < enemy.getBoughtUnits().length; i++) {
                if (enemy.getBoughtUnits()[i] != null && enemy.getBoughtUnits()[i].getHp() <= 0) {
                    enemy.getBoughtUnits()[i] = null;
                }
            }
            boolean enemyHasUnits = false;
            for (int i = 0; i < enemy.getBoughtUnits().length; i++) {
                if (enemy.getBoughtUnits()[i] != null) {
                    enemyHasUnits = true;
                }
            }
            if (!playerHasUnits) {
                drawGrid();
                System.out.println("Vesztettel!");
                System.out.println("Nyomj ENTER-t a kilepeshez");
                try {
                    System.in.read();
                } catch (Exception e) {

                }
                System.exit(0);
            } else if (!enemyHasUnits) {
                drawGrid();
                System.out.println("Nyertel!");
                System.out.println("Nyomj ENTER-t a kilepeshez");
            }
            round++;
        }
    }
    public void roundPlayer(int round) throws IOException, InterruptedException {
        int number = -1;
        for (int i = 0; i < player.getBoughtUnits().length; i++) {
            clearSelected();
            if (player.getBoughtUnits()[i] == null) {
                return;
            }
            for (int j = 0; j < player.getBoughtUnits().length; j++) {
                if (player.getBoughtUnits()[j] != null && i == j) {
                    player.getBoughtUnits()[j].setSelected(true);
                    number = j;
                    if (j > 0 && player.getBoughtUnits()[j-1] != null) {
                        player.getBoughtUnits()[j-1].setSelected(false);
                    }
                }
            }
            drawGrid();
            System.out.println(round + ". kor");
            System.out.println("Egysegied\tEletero\t\tEgysegek szama\t\tMana:" + player.getMana());
            for (int j = 0; j < player.getBoughtUnits().length; j++) {
                if (player.getBoughtUnits()[j] != null) {
                    System.out.println((player.getBoughtUnits()[j].isSelected() ? TEXT_GREEN : TEXT_RESET) +
                            player.getBoughtUnits()[j].getUnitName() + "\t\t" +
                            player.getBoughtUnits()[j].getHp() + "\t\t" + player.getBoughtUnits()[j].getUnitAmount() + TEXT_RESET);
                } else {
                    break;
                }
            }
            System.out.println("Mit szeretnel tenni az egyseggel?");
            System.out.println("1 - Mozgatas");
            System.out.println("2 - Tamadas");
            System.out.println("3 - Varakozas");
            if (!player.isUsedAttackMagic()) {
                System.out.println("\n\n\n4 - Hos tamadas");
                System.out.println("5 - Varazslat hasznalata");
            }
            System.out.println("\n6 - Egysegek adatainak megtekintese");
            Scanner sc = new Scanner(System.in);
            switch (sc.next()) {
                case "1" -> {
                    moveUnit(player.getBoughtUnits()[number]);
                    player.getBoughtUnits()[number].setSelected(false);
                }
                case "2" -> {
                    if (fightUnit(player.getBoughtUnits()[number]) == 1) {
                        i--;
                    }
                    player.getBoughtUnits()[number].setSelected(false);
                }

                case "3" -> {
                    System.out.println("Varakozas");
                    player.getBoughtUnits()[number].setSelected(false);
                    Thread.sleep(1000);
                }
                case "4" -> {
                    if (!player.isUsedAttackMagic()) {
                        playerAttack();
                    }
                    i--;
                }
                case "5" -> {
                    if (!player.isUsedAttackMagic()) {
                        playerMagic();
                    }
                    i--;
                }
                case "6" -> {
                    unitOverView();
                    i--;
                }
                default -> {
                    System.out.println("Invalid input!");
                    Thread.sleep(1000);
                    i--;
                }
            }

        }
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
    public void roundEnemy() throws IOException, InterruptedException {
        drawGrid();
        System.out.println("Enemy kore van");

        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
    public void moveUnit(Unit target) throws IOException, InterruptedException {

        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() == null) {
                    if (Math.abs(target.getPosX() - i) == (Math.abs(target.getPosY() - j)) && Math.abs(target.getPosX() - i) <= target.getSpeed()) {
                        tileArray[i][j].setCanGohere(true);
                    } else if ((Math.abs(target.getPosX() - i) + (Math.abs(target.getPosY() - j))) <= target.getSpeed()) {
                        tileArray[i][j].setCanGohere(true);
                    }
                }
            }
        }
        drawGrid();
        System.out.println("Merre szeretnel mozogni?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                int posX = -1, posY = -1;
                while (posY > 11 || posY < 0) {
                    System.out.println("X: (0-11)");
                    posY = sc.nextInt();
                }
                while (posX > 9 || posX < 0) {
                    System.out.println("Y: (0-9)");
                    posX = sc.nextInt();
                }
                if (!tileArray[posX][posY].isCanGohere()) {
                    System.out.println("Erre nem mehetsz!");
                    try
                    {
                        System.in.read();
                    }
                    catch(Exception e)
                    {}
                } else {
                    tileArray[posX][posY].setEmberOnTile(target);
                    tileArray[target.getPosX()][target.getPosY()].setEmberOnTile(null);
                    target.setPosX(posX);
                    target.setPosY(posY);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Helytelen input!");
                sc.nextLine();
                try
                {
                    System.in.read();
                }
                catch(Exception exception)
                {}
            }
        }


        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                tileArray[i][j].setCanGohere(false);
            }
        }
    }
    public int fightUnit(Unit target) throws IOException, InterruptedException {
        drawGrid();
        System.out.println("Valassz tamadast");
        if ("Ijasz".equals(target.getUnitName())) {
            System.out.println("1 - Loves");
        } else {
            System.out.println("1 - Alap tamadas");
        }
        Scanner sc = new Scanner(System.in);
        try {
            switch (sc.nextInt()) {
                case 1:
                    if (target.getUnitName() == "Ijasz") {
                        if (archerAttack(target) == 1) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else {
                        defaultAttack(target);
                        Thread.sleep(1000);
                        return 0;
                    }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ervenytelen input");
            Thread.sleep(1000);
        }
        return 1;
    }
    public void defaultAttack(Unit source) throws IOException, InterruptedException {
        for (int i = source.getPosX() - 1; i < source.getPosX() + 2; i++) {
            for (int j = source.getPosY() - 1; j < source.getPosY() + 2; j++) {
                if (tileArray[i][j].getEmberOnTile() != null && tileArray[i][j].getEmberOnTile().getParentHos() == enemy) {
                    tileArray[i][j].getEmberOnTile().setSelected(true);
                }
            }
        }
        while (true) {
            try {
                int posX = -1, posY = -1;
                drawGrid();
                System.out.println("Hol szeretnel tamadni?");
                Scanner sc = new Scanner(System.in);
                while (posY > 11 || posY < 0) {
                    System.out.println("X: (0-11)");
                    posY = sc.nextInt();
                }
                while (posX > 9 || posX < 0) {
                    System.out.println("Y: (0-9)");
                    posX = sc.nextInt();
                }
                if (tileArray[posX][posY].getEmberOnTile() != null && tileArray[posX][posY].getEmberOnTile().getParentHos()
                        == enemy && tileArray[posX][posY].getEmberOnTile().isSelected()) {
                    System.out.println(tileArray[posX][posY].getEmberOnTile().getHp());
                    source.attack(tileArray[posX][posY].getEmberOnTile());
                    tileArray[posX][posY].getEmberOnTile().getDamaged(source);
                    if (tileArray[posX][posY].getEmberOnTile().getHp() <= 0) {
                        tileArray[posX][posY].setEmberOnTile(null);
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Helytelen input!");
                try{
                    System.in.read();
                } catch (Exception exception) {

                }
            }
        }
        try{
            System.in.read();
        } catch (Exception e) {

        }
    }

    /**
     * Az íjász támadása
     * @param source Az egység, ami támadni fog
     * @return 0, ha a támadás sikeres, 1, ha nem
     * @throws IOException
     * @throws InterruptedException
     */
    public int archerAttack(Unit source) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() != null && tileArray[i][j].getEmberOnTile().getParentHos() == enemy) {
                    tileArray[i][j].getEmberOnTile().setSelected(true);
                }
            }
        }
        for (int i = source.getPosX() -1; i < source.getPosX() + 2; i++) {
            if (i >= gridSizeX || i < 0) {
                continue;
            }
            for (int j = source.getPosY() - 1; j < source.getPosY() + 2; j++) {
                if (j >= gridSizeY || j < 0) {
                    continue;
                }
                if (tileArray[i][j].getEmberOnTile() != null && tileArray[i][j].getEmberOnTile().getParentHos() == enemy) {
                    System.out.println("Nem tamadhatsz, mivel egy ellenseges egyseg a kozelben van!");
                    Thread.sleep(1000);
                    return 1;
                }
            }
        }
        while (true) {
            drawGrid();
            System.out.println("Melyik egysegre szeretnel loni?");
            try {
                int posX = -1, posY = -1;
                while (posY > 11 || posY < 0) {
                    System.out.println("X: (0-11)");
                    posY = sc.nextInt();
                }
                while (posX > 9 || posX < 0) {
                    System.out.println("Y: (0-9)");
                    posX = sc.nextInt();
                }
                if (tileArray[posX][posY].getEmberOnTile() != null &&
                        tileArray[posX][posY].getEmberOnTile().getParentHos() == enemy) {
                    source.attack(tileArray[posX][posY].getEmberOnTile());
                    tileArray[posX][posY].getEmberOnTile().getDamaged();
                    if (tileArray[posX][posY].getEmberOnTile().getHp() <= 0) {
                        tileArray[posX][posY].setEmberOnTile(null);
                    }
                    return 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ervenytelen input");
                Thread.sleep(1000);
            }
        }
    }

    public void buyUnit() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("Penzed = " + player.getBalance() + "\n");
        System.out.println("Mit szeretnel venni?");
        System.out.println("1 - Munkas      2 arany/db");
        System.out.println("2 - ijasz       6 arany/db");
        System.out.println("3 - Griff      15 arany/db");
        System.out.println("\nOpcio:");
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();

        System.out.println("\nHany darabot akarsz venni egy egysegbe?");

        int amount = sc.nextInt();

        int i;
        switch (number) {
            case 1:
                for (i = 0; i < player.getBoughtUnits().length; i++) {
                    if (player.getBoughtUnits()[i] == null) {
                        player.getBoughtUnits()[i] = new Worker(amount, player);
                        if (player.getBoughtUnits()[i].getUnitAmount() * player.getBoughtUnits()[i].getPrice() > player.getBalance()) {
                            System.out.println("Nincs eleg penzed erre!");
                            player.getBoughtUnits()[i] = null;
                        }
                        break;
                    }
                }
                player.setBalance(player.getBalance() - player.getBoughtUnits()[i].getUnitAmount() * player.getBoughtUnits()[i].getPrice());
                break;
            case 2:
                for (i = 0; i < player.getBoughtUnits().length; i++) {
                    if (player.getBoughtUnits()[i] == null) {
                        player.getBoughtUnits()[i] = new Archer(amount, player);
                        if (player.getBoughtUnits()[i].getUnitAmount() * player.getBoughtUnits()[i].getPrice() > player.getBalance()) {
                            System.out.println("Nincs eleg penzed erre!");
                            player.getBoughtUnits()[i] = null;
                        }
                        break;
                    }
                }
                player.setBalance(player.getBalance() - player.getBoughtUnits()[i].getUnitAmount() * player.getBoughtUnits()[i].getPrice());
                break;
            case 3:
                for (i = 0; i < player.getBoughtUnits().length; i++) {
                    if (player.getBoughtUnits()[i] == null) {
                        player.getBoughtUnits()[i] = new Griff(amount, player);
                        if (player.getBoughtUnits()[i].getUnitAmount() * player.getBoughtUnits()[i].getPrice() > player.getBalance()) {
                            System.out.println("Nincs eleg penzed erre!");
                            player.getBoughtUnits()[i] = null;
                        }
                        break;
                    }
                }
                player.setBalance(player.getBalance() - player.getBoughtUnits()[i].getUnitAmount() * player.getBoughtUnits()[i].getPrice());
                break;
        }
    }
    public void buyAbility() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        int abilityNumber = -1;

        while (abilityNumber < 1 || abilityNumber > 7) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            System.out.println("Melyik kepesseget szeretned fejleszteni?");
            System.out.println("1 - Tamadas     " + player.getDmgUp());
            System.out.println("2 - Vedekezes   " + player.getDefUp());
            System.out.println("3 - Varazsero   " + player.getMagicUp());
            System.out.println("4 - Tudas       " + player.getKnowledge());
            System.out.println("5 - Moral       " + player.getMoral());
            System.out.println("6 - Szerencse   " + player.getLuck());
            System.out.println("7- Vissza\n");

            System.out.print("Opcio: ");

            while (true) {
                try {
                    abilityNumber = sc.nextInt();
                    break;
                } catch (InputMismatchException exception) {
                    System.out.println("Ervenytelen input!");
                    try
                    {
                        System.in.read();
                    }
                    catch(Exception e)
                    {}
                    return;
                }
            }
        }

        if (abilityNumber == 7) {
            return;
        }

        int value = -1;
            System.out.println("Mekkora erteket szeretnel hozzaadni?");
            try {
                 value = sc.nextInt();

                 if (value > 9 || value < 1) {
                     System.out.println("Ervenytelen input!");
                     return;
                 }
            } catch (InputMismatchException exception) {
                System.out.println("Ervenytelen input!");
                try
                {
                    System.in.read();
                }
                catch(Exception e)
                {}
                return;
                }


        switch (abilityNumber) {
            case 1 -> player.setDmgUp(value);
            case 2 -> player.setDefUp(value);
            case 3 -> player.setMagicUp(value);
            case 4 -> player.setKnowledge(value);
            case 5 -> player.setMoral(value);
            case 6 -> player.setLuck(value);
        }

    }
    public void buyMagic() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("Milyen varazslatot szeretnel venni?");
        System.out.println("1 - Villam          60 arany    5 mana");
        System.out.println("2 - Tuzlabda        120 arany    9 mana");
        System.out.println("3 - Feltamasztas    120 arany    6 mana");
        System.out.println("4 - Vissza");
        System.out.print("Opcio: ");
        Scanner sc = new Scanner(System.in);
        int number;

        try {
            number = sc.nextInt();
            if (number > 5 || number < 1) {
                System.out.println("Ervenytelen input!");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Ervenytelen input!");
            return;
        }
        switch (number) {
            case 1:
                for (int i = 0; i < player.getBoughtMagic().length; i++) {
                    if (player.getBoughtMagic()[i] != null && player.getBoughtMagic()[i].getName() == "Villam") {
                        System.out.println("Mar vettel ilyen varazslataot!");
                        try
                        {
                            System.in.read();
                        }
                        catch(Exception e)
                        {}
                        return;
                    }

                    if (player.getBoughtMagic()[i] == null) {
                        player.getBoughtMagic()[i] = new Lightning(player);
                        player.setBalance(player.getBalance() - player.getBoughtMagic()[i].getPrice());
                        player.setBoughtLightning(true);
                        return;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < player.getBoughtMagic().length; i++) {
                    if (player.getBoughtMagic()[i] != null && player.getBoughtMagic()[i].getName() == "Tuzlabda") {
                        System.out.println("Mar vettel ilyen varazslataot!");
                        try
                        {
                            System.in.read();
                        }
                        catch(Exception e)
                        {}
                        return;
                    }

                    if (player.getBoughtMagic()[i] == null) {
                        player.getBoughtMagic()[i] = new Fireball(player);
                        player.setBalance(player.getBalance() - player.getBoughtMagic()[i].getPrice());
                        player.setBoughtFireball(true);
                        return;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < player.getBoughtMagic().length; i++) {
                    if (player.getBoughtMagic()[i] != null && player.getBoughtMagic()[i].getName() == "Feltamasztas") {
                        System.out.println("Mar vettel ilyen varazslataot!");
                        try
                        {
                            System.in.read();
                        }
                        catch(Exception e)
                        {}
                        return;
                    }

                    if (player.getBoughtMagic()[i] == null) {
                        player.getBoughtMagic()[i] = new Revive(player);
                        player.setBalance(player.getBalance() - player.getBoughtMagic()[i].getPrice());
                        player.setBoughtRevive(true);
                        return;
                    }
                }
                break;
        }
    }

    public void playerAttack() throws IOException, InterruptedException {
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() != null && tileArray[i][j].getEmberOnTile().getParentHos() == enemy) {
                    tileArray[i][j].getEmberOnTile().setSelected(true);
                }
            }
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                int posX = -1, posY = -1;
                drawGrid();
                System.out.println("Melyik egyseget szeretned megtamadni?");
                while (posY > 11 || posY < 0) {
                    System.out.println("X: (0-11)");
                    posY = sc.nextInt();
                }
                while (posX > 9 || posX < 0) {
                    System.out.println("Y: (0-9)");
                    posX = sc.nextInt();
                }
                if (tileArray[posX][posY].getEmberOnTile() != null && tileArray[posX][posY].getEmberOnTile().getParentHos() == enemy && tileArray[posX][posY].getEmberOnTile().isSelected()) {
                    player.attack(tileArray[posX][posY].getEmberOnTile());
                    if (tileArray[posX][posY].getEmberOnTile().getHp() <= 0) {
                        tileArray[posX][posY].setEmberOnTile(null);
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Helytelen input!");
                try{
                    System.in.read();
                } catch (Exception exception) {

                }
            }
        }
        player.setUsedAttackMagic(true);
    }
    public void playerMagic() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (player.getBoughtMagic() == null) {
                System.out.println("Nem vettel varazslatot!");
                Thread.sleep(1000);
                break;
            } else {
                drawGrid();
                System.out.println("Melyik varazslatot szeretned hasznalni?");
                int i;
                for (i = 0; i < player.getBoughtMagic().length; i++) {
                    if (player.getBoughtMagic()[i] != null) {
                        System.out.println((i + 1) + " - " + player.getBoughtMagic()[i].getName());
                    }
                }
                System.out.println("\n4 - Vissza");
                try {
                    int result = sc.nextInt();
                    if (result == 4) {
                        return;
                    }
                    switch (player.getBoughtMagic()[result - 1].getName()) {
                        case "Villam":
                            useLightning();
                            return;
                        case "Tuzlabda":
                            useFireball();
                            return;
                        case "Feltamasztas":
                            userRevive();
                            return;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Helytelen input!");
                    sc.nextLine();
                }
            }
        }
    }
    public void useLightning() throws IOException, InterruptedException {


        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() != null && tileArray[i][j].getEmberOnTile().getParentHos() == enemy) {
                    tileArray[i][j].getEmberOnTile().setSelected(true);
                }
            }
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            drawGrid();
            System.out.println("Varazslat: Villam");
            System.out.println("Melyik egyseget szeretned megtamadni?");
            try {
                int posX = -1, posY = -1;
                while (posY > 11 || posY < 0) {
                    System.out.println("X: (0-11)");
                    posY = sc.nextInt();
                }
                while (posX > 9 || posX < 0) {
                    System.out.println("Y: (0-9)");
                    posX = sc.nextInt();
                }
                if (tileArray[posX][posY].getEmberOnTile() != null && tileArray[posX][posY].getEmberOnTile().getParentHos()
                        == enemy && tileArray[posX][posY].getEmberOnTile().isSelected()) {
                    for (int i = 0; i < player.getBoughtMagic().length; i++) {
                        if (player.getBoughtMagic()[i] != null && player.getBoughtMagic()[i].getName() == "Villam") {
                            if (player.getMana() - player.getBoughtMagic()[i].getMana() < 0) {
                                System.out.println("Nincs ehhez eleg manad!");
                                Thread.sleep(1000);
                                return;
                            }
                            System.out.println(tileArray[posX][posY].getEmberOnTile().getHp());
                            player.getBoughtMagic()[i].attack(player, tileArray[posX][posY].getEmberOnTile());
                            tileArray[posX][posY].getEmberOnTile().getDamaged();
                            System.out.println(tileArray[posX][posY].getEmberOnTile().getHp());
                            player.setMana(player.getMana() - player.getBoughtMagic()[i].getMana());
                            if (tileArray[posX][posY].getEmberOnTile().getHp() <= 0) {
                                tileArray[posX][posY].setEmberOnTile(null);
                            }
                            player.setUsedAttackMagic(true);
                            return;
                        } else {
                            System.out.println("ide hogy kerultel?");
                            try {
                                System.in.read();
                            } catch (Exception e) {

                            }
                        }

                    }
                } else {
                    System.out.println("Itt nem tamadhatsz!");
                    try {
                        System.in.read();
                    } catch (Exception e) {

                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Helytelen input!");
                sc.nextLine();
                Thread.sleep(1000);
            }
        }
    }
    public void useFireball() throws IOException, InterruptedException {
        clearSelected();
        Scanner sc = new Scanner(System.in);

        while (true) {
            drawGrid();
            System.out.println("Hova szeretned dobni a tuzlabdat?");
            try {
                int posX = -1, posY = -1;
                while (posY > 11 || posY < 0) {
                    System.out.println("X: (0-11)");
                    posY = sc.nextInt();
                }
                while (posX > 9 || posX < 0) {
                    System.out.println("Y: (0-9)");
                    posX = sc.nextInt();
                }
                try {
                    for (int i = posX - 1; i < posX + 2; i++) {
                        if (i >=gridSizeX || i < 0) {
                            continue;
                        }
                        for (int j = posY - 1; j < posY + 2; j++) {
                            if (j >=gridSizeY || j < 0) {
                                continue;
                            }
                            if (tileArray[i][j].getEmberOnTile() != null) {
                                tileArray[i][j].getEmberOnTile().setSelected(true);
                            }
                        }
                    }
                    drawGrid();
                    System.out.println("Ezeket az egysegeket fogja sebzes erni. Biztos vagy benne? (y/n)");
                    switch (sc.next()) {
                        case "y":
                            for (int k = 0; k < player.getBoughtUnits().length; k++) {
                                if (player.getBoughtMagic()[k] != null &&
                                        player.getBoughtMagic()[k].getName() == "Tuzlabda") {
                                    if (player.getMana() - player.getBoughtMagic()[k].getMana() >= 0) {
                                        player.getBoughtMagic()[k].attack(player, tileArray[posX][posY].getEmberOnTile());
                                        player.setUsedAttackMagic(true);
                                        player.setMana(player.getMana() - player.getBoughtMagic()[k].getMana());
                                    } else {
                                        System.out.println("Nincs eleg manad a vasarlashoz!");
                                        Thread.sleep(1000);
                                    }
                                    return;
                                }
                            }
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {

                }
            } catch (InputMismatchException e) {
                System.out.println("Helytelen input!");
            }
        }
    }
    public void userRevive() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() != null && tileArray[i][j].getEmberOnTile().getParentHos() == player) {
                    tileArray[i][j].getEmberOnTile().setSelected(true);
                }
            }
        }
        while (true) {
            drawGrid();
            System.out.println("Feltamasztas");
            System.out.println("Melyik egysegre szeretned hasznalni?");
            try {
                int posX = -1, posY = -1;
                while (posY > 11 || posY < 0) {
                    System.out.println("X: (0-11)");
                    posY = sc.nextInt();
                }
                while (posX > 9 || posX < 0) {
                    System.out.println("Y: (0-9)");
                    posX = sc.nextInt();
                }
                if (tileArray[posX][posY].getEmberOnTile() != null && tileArray[posX][posY].getEmberOnTile().isSelected()) {
                    for (int i = 0; i < player.getBoughtMagic().length; i++) {
                        if (player.getBoughtMagic()[i] != null && player.getBoughtMagic()[i].getName() == "Feltamasztas") {
                            if (player.getMana() - player.getBoughtMagic()[i].getMana() >= 0) {
                                player.getBoughtMagic()[i].attack(player, tileArray[posX][posY].getEmberOnTile());
                                player.setMana(player.getMana() - player.getBoughtMagic()[i].getMana());
                                return;
                            } else {
                                System.out.println("Nincs eleg manad a varazslathoz!");
                            }
                        }
                    }
                } else {
                    System.out.println("Itt nem hasznalhatod a varazslatot!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Helytelen input!");
            }

        }
    }
    public void unitOverView() throws IOException, InterruptedException {
        clearSelected();
        Scanner sc = new Scanner(System.in);

        Unit focusedUnit;
        while (true) {
            drawGrid();
            System.out.println("Melyik egyseg adatait szeretned megtekinteni?");
            try {
                int posX = -1, posY = -1;
                while (posY > 11 || posY < 0) {
                    System.out.println("X: (0-11)");
                    posY = sc.nextInt();
                }
                while (posX > 9 || posX < 0) {
                    System.out.println("Y: (0-9)");
                    posX = sc.nextInt();
                }
                if (tileArray[posX][posY].getEmberOnTile() != null) {
                    while (true) {
                        focusedUnit = tileArray[posX][posY].getEmberOnTile();
                        focusedUnit.setSelected(true);
                        drawGrid();
                        System.out.println("Egyseg tipusa:\t\t" + focusedUnit.getUnitName());
                        System.out.println("Egyseg eletereje:\t" + focusedUnit.getHp());
                        System.out.println("Egysegek szama a mezon:\t" + focusedUnit.getUnitAmount());
                        System.out.println("Egyseg minimum sebzese:\t" + focusedUnit.getMinDmg());
                        System.out.println("Egyseg maximum sebzese:\t" + focusedUnit.getMaxDmg());
                        System.out.println("\n1 - Vissza");
                        try {
                            if (sc.nextInt() ==  1) {
                                return;
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Helytelen input!");
                        }
                    }
                }
                else {
                    System.out.println("Ezen a helyen nincs egyseg!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Helytelen input!");
                sc.nextLine();
            }
        }


    }

    public void clearVisszatamadott() {
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() != null) {
                    tileArray[i][j].getEmberOnTile().setVisszatamadott(false);
                }
            }
        }
    }
    public void createEnemy() {
        Random rand = new Random();
        int randomNumber = 1;

        Unit[] enemyUnits = new Unit[20];

        switch (randomNumber) {
            case 1 -> {
                enemyUnits[0] = new Worker(10, enemy);
                enemyUnits[1] = new Worker(10, enemy);
                enemyUnits[2] = new Worker(10, enemy);
                enemyUnits[3] = new Worker(10, enemy);
                enemyUnits[4] = new Worker(10, enemy);
                enemyUnits[5] = new Worker(10, enemy);
                enemyUnits[6] = new Worker(10, enemy);
                enemyUnits[7] = new Worker(10, enemy);
                enemyUnits[8] = new Griff(2, enemy);
                enemyUnits[9] = new Griff(2, enemy);
                enemyUnits[10] = new Archer(8, enemy);
                enemyUnits[11] = new Archer(8, enemy);
                enemyUnits[0].setPosX(2);
                enemyUnits[0].setPosY(11);
                enemyUnits[1].setPosX(2);
                enemyUnits[1].setPosY(10);
                enemyUnits[2].setPosX(3);
                enemyUnits[2].setPosY(10);
                enemyUnits[3].setPosX(4);
                enemyUnits[3].setPosY(10);
                enemyUnits[4].setPosX(5);
                enemyUnits[4].setPosY(10);
                enemyUnits[5].setPosX(6);
                enemyUnits[5].setPosY(10);
                enemyUnits[6].setPosX(7);
                enemyUnits[6].setPosY(10);
                enemyUnits[7].setPosX(7);
                enemyUnits[7].setPosY(11);
                enemyUnits[8].setPosX(4);
                enemyUnits[8].setPosY(11);
                enemyUnits[9].setPosX(5);
                enemyUnits[9].setPosY(11);
                enemyUnits[10].setPosX(3);
                enemyUnits[10].setPosY(11);
                enemyUnits[11].setPosX(6);
                enemyUnits[11].setPosY(11);

                enemy.setBoughtUnits(enemyUnits);

                for (int i = 0; i < enemy.getBoughtUnits().length; i++) {
                    if (enemy.getBoughtUnits()[i] != null) {
                        tileArray[enemy.getBoughtUnits()[i].getPosX()][enemy.getBoughtUnits()[i].getPosY()].setEmberOnTile(enemy.getBoughtUnits()[i]);
                    }
                }
                enemy.setDmgUp(4);
                enemy.setDefUp(3);
                enemy.setMagicUp(2);

            }
        }
    }
    public void clearSelected() {
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() != null) {
                    tileArray[i][j].getEmberOnTile().setSelected(false);
                }
            }
        }
    }
}