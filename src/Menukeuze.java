import java.util.Scanner;

interface keuze {
    void menu();
}

class Menukeuze implements keuze {
    static Scanner scanner = new Scanner(System.in);
    static Speler speler;
    static Game game;

    @Override
    public void menu() {
        System.out.println("1. Start spel");
        System.out.println("2. Login");
        System.out.println("3. Afsluiten");
        System.out.print("Maak een keuze: ");

        int keuze = scanner.nextInt();
        scanner.nextLine();

        switch (keuze) {
            case 1:
                System.out.print("Voer je naam in: ");
                String naam = scanner.nextLine();
                speler = new Speler(naam, 1);
                game = new Game(speler);

                game.storyline();
                game.startGame();
                break;
            case 2:
                System.out.println("Login");
                break;
            case 3:
                System.out.println("Programma wordt afgesloten.");
                System.exit(0);
                break;
            default:
                System.out.println("Ongeldige keuze, probeer opnieuw.");
                break;
        }
    }
}
