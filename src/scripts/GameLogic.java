package scripts;

import units.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class GameLogic {


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

        while (true) {
            if (gameState == 1) {
                break;
            }
            buyUnits();
        }

        fightPhase();

    }

    public void fightPhase() {
        enemy = new Hos();
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

            if (i + 0 < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
            System.out.print("|");
            for (int j = 0; j < gridSizeY; j++) {
                if (tileArray[i][j].getEmberOnTile() != null) {
                    System.out.print(" " + tileArray[i][j].getEmberOnTile().getUnitName() + " ");
                } else {
                    System.out.print("   ");
                }
                System.out.print("|");
            }
            System.out.println("");
        }
    }

    public void buyUnits() throws IOException, InterruptedException {
        drawGrid();
        System.out.println("Penzed = " + player.getBalance());
        System.out.println("Mit szeretnel tenni?");
        System.out.println("1 - Munkas      2 arany/db");
        System.out.println("2 - ijasz       6 arany/db");
        System.out.println("3 - Griff      15 arany/db");
        System.out.println("4 - Kepessegek fejlesztese");
        System.out.println("5 - Varazslatok vasarlasa");
        System.out.println("6 - Start");
        System.out.println("7 - Kilepes\n\n");
        System.out.println("Tulajdonsagok:");
        System.out.println("Tamadas     " + player.getDmgUp());
        System.out.println("Vedekezes   " + player.getDefUp());
        System.out.println("Varazsero   " + player.getMagicUp());
        System.out.println("Tudas       " + player.getKnowledge());
        System.out.println("Moral       " + player.getMoral());
        System.out.println("Szerencse   " + player.getLuck());
        Scanner sc = new Scanner(System.in);
        System.out.print("Opcio: ");
        int number = sc.nextInt();

        switch (number) {
            case 4 -> {
                buyAbility();
                return;
            }
            case 5 -> {
                buyMagic();
                return;
            }
            case 6 -> {
                for (int i = 0; i < gridSizeX; i++) {
                    for (int j = 0; j < gridSizeY; j++) {
                        if (tileArray[i][j].getEmberOnTile() != null) {
                            gameState = 1;
                            System.out.println("Kezdodhet a moka");
                            return;
                        }
                    }
                }
                System.out.println("Eloszor tegyel le egy egyseget!");
                try {
                    System.in.read();
                } catch (Exception e) {
                }
                return;
            }
            case 7 -> {
                System.out.println("Biztos ki akarsz lepni a jatekbol? A nem mentett modositasok elvesznek! (y/n)");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.next();
                if (Objects.equals(answer, "y") || Objects.equals(answer, "Y")) {
                    System.exit(0);
                } else {
                    return;
                }
            }
        }

        int posX = -1;
        int posY = -1;

        int unitAmount;

        while (posX < 0 || posX > 9) {
            System.out.println("Hova szeretned rakni?");
            System.out.print("X (0-9): ");
            posX = sc.nextInt();
        }

        while (posY < 0 || posY > 1) {
            System.out.print("Y (0-1): ");
            posY = sc.nextInt();
        }

        System.out.println("Hany egyseget akarsz lerakni?");
        unitAmount = sc.nextInt();
        System.out.println(unitAmount);
        System.out.println(posX);
        System.out.println(posY);

        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}

        if (tileArray[posX][posY].getEmberOnTile() != null) {
            System.out.println("Ez a hely foglalt!");
            try
            {
                System.in.read();
            }
        catch(Exception e)
            {}
            return;
        }


        switch (number) {
            case 1:
                tileArray[posX][posY].setEmberOnTile(new Worker());
                if (player.getBalance() - tileArray[posX][posY].getEmberOnTile().getPrice() * tileArray[posX][posY].getEmberOnTile().getUnitAmount() < 0) {
                    System.out.println("Nincs ehhez eleg aranyad!");
                    tileArray[posX][posY].setEmberOnTile(null);
                } else {
                    player.setBalance(player.getBalance() - tileArray[posX][posY].getEmberOnTile().getPrice() * tileArray[posX][posY].getEmberOnTile().getUnitAmount());
                }
                break;
            case 2:
                tileArray[posX][posY].setEmberOnTile(new Archer());
                if (player.getBalance() - tileArray[posX][posY].getEmberOnTile().getPrice() * tileArray[posX][posY].getEmberOnTile().getUnitAmount() < 0) {
                    System.out.println("Nincs ehhez eleg aranyad!");
                    tileArray[posX][posY].setEmberOnTile(null);
                } else {
                    player.setBalance(player.getBalance() - tileArray[posX][posY].getEmberOnTile().getPrice() * tileArray[posX][posY].getEmberOnTile().getUnitAmount());
                }
                break;
            case 3:
                tileArray[posX][posY].setEmberOnTile(new Griff());
                if (player.getBalance() - tileArray[posX][posY].getEmberOnTile().getPrice() * tileArray[posX][posY].getEmberOnTile().getUnitAmount() < 0) {
                    System.out.println("Nincs ehhez eleg aranyad!");
                    tileArray[posX][posY].setEmberOnTile(null);
                } else {
                    player.setBalance(player.getBalance() - tileArray[posX][posY].getEmberOnTile().getPrice() * tileArray[posX][posY].getEmberOnTile().getUnitAmount());
                }
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
            case 1:
                player.setDmgUp(value);
                break;
            case 2:
                player.setDefUp(value);
                break;
            case 3:
                player.setMagicUp(value);
                break;
            case 4:
                player.setKnowledge(value);
                break;
            case 5:
                player.setMoral(value);
                break;
            case 6:
                player.setLuck(value);
                break;

        }

    }


    public void buyMagic() {
        System.out.println();
    }
}
