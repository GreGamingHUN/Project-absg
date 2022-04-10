package scripts;

import units.*;

import java.io.IOException;
import java.util.Scanner;


public class GameLogic {


    //Game States
    //0 = buying phase
    //1 = fight phase

    int gameState = 0;

    Hos player;
    Hos enemy;

    int gridSizeX = 10;
    int gridSizeY = 12;
    Tile[][] tileArray = new Tile[gridSizeX][gridSizeY];
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
            buyPhase();
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

    public void buyPhase() throws IOException, InterruptedException {
        drawGrid();
        System.out.println("Penzed = " + player.getBalance());
        System.out.println("Mit szeretnel tenni?");
        System.out.println("1 - Munkas");
        System.out.println("2 - ijasz");
        System.out.println("3 - Griff");
        System.out.println("4 - Kepessegek fejlesztese");
        System.out.println("5 - Start");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        if (number == 5) {
            for (int i = 0; i < gridSizeX; i++) {
                for (int j = 0; j < gridSizeY; j++) {
                    if(tileArray[i][j].getEmberOnTile() != null) {
                        gameState = 1;
                        System.out.println("Kezdodhet a moka");
                        return;
                    }
                }
            }
            System.out.println("Eloszor tegyel le egy egyseget!");
            try
            {
                System.in.read();
            }
            catch(Exception e)
            {}
            return;
        }

        if (number == 4) {
            abilityChange();
            return;
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

        System.out.println("Hany egységet akarsz lerakni?");
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
                player.setBalance(player.getBalance() - tileArray[posX][posY].getEmberOnTile().getPrice());
                break;
            case 2:
                tileArray[posX][posY].setEmberOnTile(new Archer());
                player.setBalance(player.getBalance() - tileArray[posX][posY].getEmberOnTile().getPrice());
                break;
            case 3:
                tileArray[posX][posY].setEmberOnTile(new Griff());
                player.setBalance(player.getBalance() - tileArray[posX][posY].getEmberOnTile().getPrice());
                break;
        }
    }

    public void abilityChange () throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        int abilityNumber = -1;

        while (abilityNumber < 1 || abilityNumber > 7) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            System.out.println("Melyik kepesseget szeretned fejleszteni?");
            System.out.println("1 - Tamadas");
            System.out.println("2 - Vedekezes");
            System.out.println("3 - Varazsero");
            System.out.println("4 - Tudas");
            System.out.println("5 - Moral");
            System.out.println("6 - Szerencse");
            System.out.println("7- Vissza");

            abilityNumber = sc.nextInt();
        }

        int value = -1;

        while (value < 1 || value > 10) {
            System.out.println("Mekkora erteket szeretnel neki adni?");
            value = sc.nextInt();
        }

        player.setBalance(player.getBalance() - player.getAbilityPrice());
        player.setAbilityPrice((int)Math.ceil((double)player.getAbilityPrice() * 1.1));

        switch (abilityNumber) {
            case 1:
                player.setDmgUp(value);
            case 2:
                player.setDefUp(value);
            case 3:
                player.setMagicUp(value);
            case 4:
                player.setKnowledge(value);
            case 5:
                player.setMoral(value);
            case 6:
                player.setLuck(value);

        }

    }

}
