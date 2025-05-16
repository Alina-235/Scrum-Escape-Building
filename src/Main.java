//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Database.getConnection();
        System.out.println("Welkom bij Scrum Escape Buidling!");

        menu.MainMenu();

        while (!game.GameOver()) {
            game.startGame();
        }
    }
}