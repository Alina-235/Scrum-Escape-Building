import java.util.Scanner;

interface keuze {
    void menu(String naam);
}

class Menukeuze implements keuze {
    private final Scanner scanner = new Scanner(System.in);
    private Speler speler;
    private Game game;
    private final databaseSelect db = new databaseSelect();

    @Override
    public void menu(String naam) {
        System.out.print("Maak uw keuze: ");
        int keuze = scanner.nextInt();
        switch (keuze) {
                case 1:
                    startGame(naam);
                    break;
                case 2:
                    loginSpeler(naam);
                    break;
                case 3:
                    System.out.println("Programma wordt afgesloten.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
                    Menu.mainMenu(naam);
            }
    }

    private void startGame(String naam) {
        speler = db.SpelerLogin(naam);
        if (speler == null) {
            System.out.println("Kan speler niet laden.");
            return;
        }

        System.out.println("Kies je joker (typ 'hint' of 'key'):");
        scanner.nextLine();
        String jokerKeuze = scanner.nextLine().toLowerCase();

        if (jokerKeuze.equals("hint")) {
            speler.kiesJoker(new HintJoker(new DatabaseHintRepository()));
        } else if (jokerKeuze.equals("key")) {
            speler.kiesJoker(new KeyJoker(new DatabaseVragenRepository()));
        } else {
            System.out.println("Geen geldige joker gekozen. Je krijgt geen joker.");
        }

        game = new Game(speler);
        game.storyline();
        game.startGame();

        new Bewegen(speler).bewegen();
    }


    private void loginSpeler(String naam) {
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