import java.util.Scanner;

class Menukeuze {
    static int keuze;
    static Scanner scanner =  new Scanner(System.in);
    static Speler speler = new Speler("Gebruiker1", 1);
    static Game game = new Game(speler);

    public static void mainmenukeuze(){
        keuze = scanner.nextInt();
        scanner.nextLine();
        switch (keuze){
            case 1:
                game.storyline();
                game.startGame();
                break;
            case 2:
                //speler.inloggen();
            case 3:
                System.exit(0);
                break;
        }
    }
}