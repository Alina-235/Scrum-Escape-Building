public class Main {
    static Speler speler = new Speler();  // New player structure
    static Menu menu = new Menu();
    static Game game = new Game(speler);

    public static void main(String[] args) {
        System.out.println("Welkom bij Scrum Escape Building!");

        if (!speler.isIngelogd()) {
            menu.MenuInloggen(speler);
        }

        while (!game.gameover()) {
            menu.Mainmenu(game);
        }
    }
}
