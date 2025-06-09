import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Database.getConnection();
        System.out.println("Welkom bij Scrum Escape Building!");

        System.out.print("Voer je naam in: ");
        String naam = scanner.nextLine();
        databaseSelect dbSelect = new databaseSelect();
        Speler speler = dbSelect.SpelerLogin(naam);


        speler.addObserver(new Feedback());
        speler.addObserver(new Deur());
        speler.addObserver(new Logger());

        GameController controller = new GameController(speler);
        controller.startGame();
        controller.showStatus();


        Menu menu = new Menu();
        menu.mainMenu();
    }
}


