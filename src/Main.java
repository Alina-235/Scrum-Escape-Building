public class Main {
    static Game game = new Game (new Speler());
    static Speler speler = new Speler();
    static Menu menu = new Menu();

    public static void main(String[] args) {
        Database.getConnection();
        System.out.println("Welkom bij Scrum Escape Buidling!");

        menu.MainMenu();

        while (!game.GameOver()) {
            game.startGame();
        }
    }
}