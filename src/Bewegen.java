import java.util.ArrayList;
import java.util.Scanner;

interface Move {
    void bewegen();
}

public class Bewegen implements Move {
    private Speler speler;
    private final databaseSelect db = new databaseSelect();
    private static final Scanner scanner = new Scanner(System.in);

    public Bewegen(Speler speler) {
        this.speler = speler;
    }

    @Override
    public void bewegen() {
        boolean gameOver = false;

        while (!gameOver && speler.getLives() > 0) {
            Kamer huidigeKamer = speler.getHuidigeKamer();
            //System.out.println("Je bent nu in kamer: " + huidigeKamer.getNaam());

            ArrayList<Vragen> vragen = db.getVragenVoorKamer(huidigeKamer.getKamerId());

            if (vragen.isEmpty()) {
                System.out.println("Geen vragen in deze kamer.");
            } else {
                for (Vragen vraag : vragen) {
                    boolean correct = false;
                    while (!correct && speler.getLives() > 0) {
                        System.out.println("Vraag: " + vraag.getVraag());
                        System.out.print("Jouw antwoord: ");
                        String antwoord = scanner.nextLine();

                        ArrayList<String> antwoorden = new ArrayList<>();
                        antwoorden.add(antwoord);

                        if (vraag.checkAnswer(antwoorden)) {
                            System.out.println("Correct!");
                            correct = true;
                        } else {
                            System.out.println("Fout! Je verliest een leven.");
                            speler.attacked();

                            if (speler.getLives() <= 0) {
                                System.out.println("Je hebt geen levens meer. Game over.");
                                return;
                            }

                            System.out.println("Probeer het opnieuw.");
                        }
                    }
                }

                Kamer volgende = db.getKamerById(huidigeKamer.getKamerId() + 1);
                if (volgende != null) {
                    speler.moveTo(volgende);
                    continue;
                } else {
                    System.out.println("Je hebt het spel voltooid!");
                    break;
                }
            }

            System.out.println("Gebruik 'a' voor terug, 'd' voor verder, of 'x' om te stoppen:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("x")) {
                System.out.println("Spel beëindigd.");
                gameOver = true;
            } else if (input.equalsIgnoreCase("a")) {
                Kamer vorige = db.getKamerById(huidigeKamer.getKamerId() - 1);
                if (vorige != null) speler.moveTo(vorige);
                else System.out.println("Geen vorige kamer.");
            } else if (input.equalsIgnoreCase("d")) {
                Kamer volgende = db.getKamerById(huidigeKamer.getKamerId() + 1);
                if (volgende != null) speler.moveTo(volgende);
                else System.out.println("Je bent bij de laatste kamer.");
            } else {
                System.out.println("Ongeldige invoer.");
            }
        }
    }
}
