import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Database.getConnection();

        System.out.println("Welkom bij Scrum Escape Building!");
        System.out.print("Voer je naam in: ");
        String naam = scanner.nextLine();

        Speler speler = new Speler(naam, 1);
        speler.addObserver(new Feedback());

        GameController controller = new GameController(speler);

        boolean doorgaan = true;
        while (doorgaan && !controller.isGameOver()) {
            int keuze = Menukeuze.toonMenuEnKrijgKeuze();
            doorgaan = controller.verwerkKeuze(keuze);
        }
    }
}


