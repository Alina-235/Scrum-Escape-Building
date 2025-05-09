public class Main {
    static Speler speler = new Speler();  // New player structure
    static Menu menu = new Menu();
    static Game game = new Game(speler);
    Database database = new Database();

    public static void main(String[] args) {
        database.getConnection();
        System.out.println("Welkom bij Scrum Escape Building!");

        if (!speler.isIngelogd()) {
            menu.MenuInloggen(speler);
        }

        while (!game.GameOver()) {
            menu.MainMenu(game);
        }
    }
}
