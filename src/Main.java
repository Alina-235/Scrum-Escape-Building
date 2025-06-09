public class Main {
    static Speler speler = new Speler("", 1);
    static Menu menu = new Menu();
    static Game game = new Game();

    public static void main(String[] args) {
        Database.getConnection();
        System.out.println("Welkom bij Scrum Escape Building!");

        speler.addObserver(new Feedback());
        speler.addObserver(new Deur());
        speler.addObserver(new Logger());
        game = new Game();

        menu.mainMenu();
    }
}