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
        System.out.print("Maak uw keuze: ");
        String input = scanner.nextLine();

        int keuzeNummer;
        try {
            keuzeNummer = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer, typ een getal (1-3).");
            return; // Exit the method, avoid crashing
        }

        switch (keuzeNummer) {
            case 1:
                System.out.print("Voer je naam in: ");
                String naam = scanner.nextLine();
                speler = new databaseSelect().SpelerLogin(naam);
                game = new Game(speler);

                game.storyline();
                game.startGame();

                new Bewegen(speler).bewegen();
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


