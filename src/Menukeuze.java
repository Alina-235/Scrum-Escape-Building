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
        int keuzeNummer = scanner.nextInt();
        scanner.nextLine();

        switch (keuzeNummer) {
            case 1:
                System.out.print("Voer je naam in: ");
                String naam = scanner.nextLine();
                speler = new databaseSelect().SpelerLogin(naam);

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


