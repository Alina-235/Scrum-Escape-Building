import java.util.Scanner;

public class Main {
    static Speler speler = new Speler("", 0);
    static Game game = new Game (speler);
    static Menu menu = new Menu();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Database.getConnection();
        System.out.println("Welkom bij Scrum Escape Buidling!");
        game.toonStatus();
        speler.maakSpeler();
        menu.mainMenu();
        game.startGame();
    }
}