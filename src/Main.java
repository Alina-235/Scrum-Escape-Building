public class Main {

    public static void main(String[] args) {
        Database.getConnection();
        System.out.println("Welkom bij Scrum Escape Building!");

        if (Speler.isIngelogd()) {
            Menu.MenuInloggen();
        }

        while (Game.GameOver()) {
            Menu.MainMenu();
        }
    }
}
