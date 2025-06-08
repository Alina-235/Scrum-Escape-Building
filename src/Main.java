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
        speler.addObserver(new Deur());
        speler.addObserver(new Logger());

        GameController controller = new GameController(speler);

        Menu menu = new Menu();
        menu.mainMenu();
    }
}


