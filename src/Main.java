import scripts.GameLogic;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("Udvozollek a Project-ABSG-ben!");
        System.out.println("Valassz nehezseget:");


        System.out.println("1 - Konnyu");
        System.out.println("2 - Kozepes");
        System.out.println("3 - Nehez");

        Scanner sc = new Scanner(System.in);
        int diff = sc.nextInt();
        GameLogic gl = new GameLogic(diff);
        System.out.println(diff);
        gl.startGame();
    }



}
