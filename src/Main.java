public class Main {
    public static void main(String[] args) {
        Database.getConnection();
        System.out.println("Welkom bij Scrum Escape Building!");

        Menu menu = new Menu();
        menu.mainMenu();
    }
}
