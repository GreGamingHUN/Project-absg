import scripts.GameLogic;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner sc = new Scanner(System.in);
        int diff;
        while (true) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("Udvozollek a Project-ABSG-ben!");
            System.out.println("Valassz nehezseget:");


            System.out.println("1 - Konnyu");
            System.out.println("2 - Kozepes");
            System.out.println("3 - Nehez");
            try {
                diff = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException exception) {
                System.out.println("Ervenytelen input");
                Thread.sleep(1000);
                System.exit(1);
            }
        }



        GameLogic gl = new GameLogic(diff);
        System.out.println(diff);
        gl.startGame();
    }



}
