import java.util.Scanner;


interface keuze {
    void menu();
}

class Menukeuze implements keuze {
    private final Scanner scanner = new Scanner(System.in);
    private Speler speler;
    private Game game;
    private final databaseSelect db = new databaseSelect();

    @Override
    public void menu() {
        boolean running = true;

        while (running) {
            toonHoofdmenu();
            String input = scanner.nextLine();

            int keuzeNummer;
            try {
                keuzeNummer = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer, typ een getal (1-3).");
                continue;
            }

            switch (keuzeNummer) {
                case 1:
                    startGame();
                    break;
                case 2:
                    loginSpeler();
                    break;
                case 3:
                    System.out.println("Programma wordt afgesloten.");
                    running = false;
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }

    private void toonHoofdmenu() {
        System.out.println("\n--- Scrum Escape Building ---");
        System.out.println("1. Start Game");
        System.out.println("2. Log in");
        System.out.println("3. Exit");
        System.out.print("Maak uw keuze: ");
    }

    private void startGame() {
        System.out.print("Voer je naam in: ");
        String naam = scanner.nextLine();

        speler = db.SpelerLogin(naam);
        if (speler == null) {
            System.out.println("Kan speler niet laden.");
            return;
        }

        // Joker keuze prompt
        System.out.println("Kies je joker (typ 'hint' of 'key'):");
        String jokerKeuze = scanner.nextLine().toLowerCase();

        if (jokerKeuze.equals("hint")) {
            speler.kiesJoker(new HintJoker());
        } else if (jokerKeuze.equals("key")) {
            speler.kiesJoker(new KeyJoker());
        } else {
            System.out.println("Geen geldige joker gekozen. Je krijgt geen joker.");
        }

        game = new Game(speler);
        game.storyline();
        game.startGame();

        new Bewegen(speler).bewegen();
    }


    private void loginSpeler() {
        System.out.print("Voer je naam in: ");
        String naam = scanner.nextLine();

        speler = db.SpelerLogin(naam);
        if (speler != null) {
            System.out.println(" Welkom terug, " + speler.getNaam());
            speler.toonStatus();
            new Bewegen(speler).bewegen();
        } else {
            System.out.println(" Speler niet gevonden.");
        }
    }
}