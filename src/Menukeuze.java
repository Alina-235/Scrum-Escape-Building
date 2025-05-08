import java.util.Scanner;

public class Menukeuze {
    static Scanner scanner = new Scanner(System.in);

    public void MenuInloggenKeuze(Speler speler) {
        System.out.print("\nMaak een keuze: ");
        int keuze = scanner.nextInt();
        scanner.nextLine();

        switch (keuze) {
            case 1:
                speler.inloggen();
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

    public void HoofdmenuKeuze(Game game) {
        System.out.print("\nMaak een keuze: ");
        int keuze = scanner.nextInt();
        scanner.nextLine();

        switch (keuze) {
            case 1:
                game.startSpel();
                break;
            case 2:
                game.toonStatus();
                break;
            case 3:
                game.stopSpel();
                break;
        }
    }
}
