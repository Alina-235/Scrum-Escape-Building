import java.util.Scanner;

public class Main {
    static Speler speler = new Speler("", 0);
    static Menu menu = new Menu();

    public static void main(String[] args) {
        Database.getConnection();
        System.out.println("Welkom bij Scrum Escape Buidling!");

        menu.mainMenu();
    }
}