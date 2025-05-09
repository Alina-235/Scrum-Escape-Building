import java.util.Scanner;

class Menukeuze {
    static int keuze;
    static Scanner scanner =  new Scanner(System.in);

    public static void mainmenukeuze(){
        keuze = scanner.nextInt();
        switch (keuze){
            case 1:
                Game.startGame();
                break;
            case 2:
                System.exit(0);
                break;
        }
    }
}
