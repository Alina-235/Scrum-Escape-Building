import java.util.Scanner;

public class Menukeuze {
    private static final Scanner scanner = new Scanner(System.in);

    public static int toonMenuEnKrijgKeuze() {
        System.out.println("Maak een keuze:");
        System.out.println("1. Start spel");
        System.out.println("2. Credits");
        System.out.println("3. Afsluiten");
        System.out.print("Keuze: ");
        int keuze = scanner.nextInt();
        scanner.nextLine();
        return keuze;
    }
}
 class GameController {
    private Game game;
    private GameStory story;

    public GameController(Speler speler) {
        this.game = new Game();
        this.game.voegSpelerToe(speler);
        this.story = new GameStory();
    }

    public boolean verwerkKeuze(int keuze) {
        switch (keuze) {
            case 1:
                story.toonIntro();
                game.startGame();
                return true;
            case 2:
                toonCredits();
                return true;
            case 3:
                System.out.println("Programma wordt afgesloten.");
                return false;
            default:
                System.out.println("Ongeldige keuze, probeer opnieuw.");
                return true;
        }
    }

    private void toonCredits() {
        System.out.println("Escape Scrum Building © 2025");
        System.out.println("Gemaakt door jouw projectteam.");
    }

    public boolean isGameOver() {
        return game.isGameOver();
    }
}


