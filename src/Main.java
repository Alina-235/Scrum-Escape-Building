public class Main {
    static Menu menu = new Menu();

    public static void main(String[] args) {
        Database.getConnection();
        System.out.println("Welkom bij Scrum Escape Buidling!");

        menu.mainMenu();
    }
}