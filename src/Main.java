import java.util.Scanner;

public class Main {

    static Game game;
    static Speler speler;
    static Menu menu = new Menu();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Database.getConnection();

        System.out.println("Welkom bij Scrum Escape Building!");
        System.out.print("Voer je naam in: ");
        String naam = scanner.nextLine();

        speler = new Speler(naam, 1);
        game = new Game(speler);

        menu.MainMenu();

        while (!game.GameOver()) {
            game.startGame();
        }
    }
}
