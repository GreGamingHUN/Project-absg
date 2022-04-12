package scripts;

import magics.Fireball;
import magics.Lightning;
import magics.Revive;
import units.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class GameLogic {
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RESET = "\u001B[0m";

    //Game States
    //0 = buying phase
    //1 = fight phase

    int gameState = 0;

    Hos player;
    Hos enemy;

    static int gridSizeX = 10;
    static int gridSizeY = 12;
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
        fightPhase();
    }

    public void fightPhase() {
        enemy = new Hos();
        System.out.println("Fight phase started");

        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
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

            if (i < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
            System.out.print(TEXT_GREEN + "|" + TEXT_RESET);
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() != null) {
                    System.out.print(" " + tileArray[i][j].getEmberOnTile().getUnitShort() + " ");
                } else {
                    System.out.print("   ");
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

        //region get magic amounts
        int lightningAmount = 0, fireballAmount = 0, reviveAmount = 0;

        for (int i = 0; i < player.getBoughtMagic().length; i++) {
            if (player.getBoughtMagic()[i] != null) {
                switch (player.getBoughtMagic()[i].getName()) {
                    case "lightning" -> lightningAmount = player.getBoughtMagic()[i].getAmount();
                    case "fireball" -> fireballAmount = player.getBoughtMagic()[i].getAmount();
                    case "revive" -> reviveAmount = player.getBoughtMagic()[i].getAmount();
                }
            }
        }

        //endregion

        System.out.println("Tamadas:     " + player.getDmgUp()+     "               Villam:          " + lightningAmount);
        System.out.println("Vedekezes:   " + player.getDefUp()+     "               Tuzlabda:        " + fireballAmount);
        System.out.println("Varazsero:   " + player.getMagicUp()+   "               Feltamasztas:    " + reviveAmount);
        System.out.println("Tudas:       " + player.getKnowledge());
        System.out.println("Moral:       " + player.getMoral());
        System.out.println("Szerencse:   " + player.getLuck());
        Scanner sc = new Scanner(System.in);
        System.out.print("\nOpcio: ");
        int number = sc.nextInt();

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
                int result = scanner.nextInt();
                if (result == 1) {
                    break;
                }
            }
            case 5 -> {
                gameState = 1;
                placementPhase();
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
                System.out.print("\nX pozicio (0-9):");
                int posX = -1;
                while (posX > 9 || posX < 0) {
                    System.out.print("\nX pozicio (0-9):");
                    posX = sc.nextInt();
                }
                player.getBoughtUnits()[i].setPosX(posX);

                System.out.println("Y pozicio (0-1):");
                int posY = -1;
                while (posY > 1 || posY < 0) {
                    System.out.println("Y pozicio (0-1):");
                    posY = sc.nextInt();
                }
                player.getBoughtUnits()[i].setPosY(posY);

                tileArray[player.getBoughtUnits()[i].getPosX()][player.getBoughtUnits()[i].getPosY()].
                        setEmberOnTile(player.getBoughtUnits()[i]);
            }
        }

        System.out.println("\nMit szeretnel tenni?");
        System.out.println("1 - Start");
        System.out.println("2 - Kilepes");
        System.out.print("Opcio: ");
        Scanner sc = new Scanner(System.in);
        if (sc.nextInt() == 1) {
            return;
        }
    }

    public void buyUnit() {
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
                        player.getBoughtUnits()[i] = new Worker(amount);
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
                        player.getBoughtUnits()[i] = new Archer(amount);
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
                        player.getBoughtUnits()[i] = new Griff(amount);
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
            abilityNumber = sc.nextInt();
        }

        if (abilityNumber == 7) {
            return;
        }

        int value = -1;

        while (value < 1 || value > 10) {
            System.out.println("Mekkora erteket szeretnel hozzaadni?");
            value = sc.nextInt();
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
        System.out.println("1 - Villam          60 arany/db     5 mana");
        System.out.println("2 - Tuzlabda        120 arany/db    9 mana");
        System.out.println("3 - Feltamasztas    120 arany/db    6 mana");
        System.out.println("4 - Vissza");
        System.out.print("Opcio: ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        System.out.println("Hany darabot szeretnel venni? (EGY VARAZSLATOT CSAK EGY ALKALOMMAL VASAROLHATSZ)");
        int amount = sc.nextInt();

        switch (number) {
            case 1:
                for (int i = 0; i < player.getBoughtMagic().length; i++) {
                    if (player.getBoughtMagic()[i] != null && player.getBoughtMagic()[i].getName() == "lightning") {
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
                        player.getBoughtMagic()[i] = new Lightning(amount);
                        player.setBalance(player.getBalance() - player.getBoughtMagic()[i].getPrice() * amount);
                        return;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < player.getBoughtMagic().length; i++) {
                    if (player.getBoughtMagic()[i] != null && player.getBoughtMagic()[i].getName() == "fireball") {
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
                        player.getBoughtMagic()[i] = new Fireball(amount);
                        player.setBalance(player.getBalance() - player.getBoughtMagic()[i].getPrice() * amount);
                        return;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < player.getBoughtMagic().length; i++) {
                    if (player.getBoughtMagic()[i] != null && player.getBoughtMagic()[i].getName() == "revive") {
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
                        player.getBoughtMagic()[i] = new Revive(amount);
                        player.setBalance(player.getBalance() - player.getBoughtMagic()[i].getPrice() * amount);
                        return;
                    }
                }
                break;
        }
    }
}
