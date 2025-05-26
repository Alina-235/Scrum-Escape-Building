import java.util.Scanner;

public class Menukeuze {
    static Scanner scanner = new Scanner(System.in);
    static Speler speler = new Speler("Gebruiker1", 1);
    static Game game = new Game(speler);

    public static boolean handleChoice() {
        int keuze = scanner.nextInt();
        scanner.nextLine();

        switch (keuze) {
            case 1:
                game.storyline();
                game.startGame();
                return true;
            case 2:
                return true;
            case 3:
                System.out.println("Programma wordt afgesloten.");
                return false;
            default:
                System.out.println("Ongeldige keuze, probeer opnieuw.");
                return true;
        }
    }
}
