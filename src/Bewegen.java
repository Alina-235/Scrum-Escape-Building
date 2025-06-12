import java.util.ArrayList;
import java.util.Scanner;

interface Move {
    void bewegen();
}

public class Bewegen implements Move {
    private Speler speler;
    private final databaseSelect db = new databaseSelect();
    private static final Scanner scanner = new Scanner(System.in);
    static Update update = new Update();
    static databaseSelect select = new databaseSelect();

    public Bewegen(Speler speler) {
        this.speler = speler;
    }

    @Override
    public void bewegen() {
        boolean gameOver = false;

        while (!gameOver && speler.getLives() > 0) {
            Kamer huidigeKamer = speler.getHuidigeKamer();
            ArrayList<Vragen> vragen = db.getVragenVoorKamer(huidigeKamer.getKamerId());

            if (vragen.isEmpty()) {
                System.out.println("Geen vragen in deze kamer.");
            } else {
                for (Vragen vraag : vragen) {
                    boolean correct = false;
                    while (!correct && speler.getLives() > 0) {
                        System.out.println("Vraag: " + vraag.getVraag());
                        System.out.println("Typ je antwoord, of typ 'assistent' voor hulp (hint + uitleg + motivatie), of typ 'joker' om je gekozen joker te gebruiken.");

                        String antwoord = scanner.nextLine().toLowerCase();

                        if (antwoord.equals("assistent")) {
                            Assistent assistent = new Assistent();
                            assistent.activeer(speler.getHuidigeKamer(), vraag);
                            continue;
                        } else if (antwoord.equals("joker")) {
                            int monstersVoor = speler.getActieveMonsters().size();
                            speler.gebruikJoker(speler.getHuidigeKamer());
                            int monstersNa = speler.getActieveMonsters().size();

                            if (monstersNa < monstersVoor) {
                                System.out.println("Je hebt deze uitdaging overgeslagen.");
                                break;
                            }
                            continue;
                        }

                        ArrayList<String> antwoorden = new ArrayList<>();
                        antwoorden.add(antwoord);

                        if (vraag.checkAnswer(antwoorden)) {
                            System.out.println("Correct!");
                            correct = true;
                        } else {
                            System.out.println("Fout!");
                            Monster monster = MonsterFactory.getRandomMonster();
                            System.out.println("In deze kamer vind je het monster: " + monster.getNaam());
                            System.out.println(monster.getMonsterDesign());
                            System.out.println("Beschrijving: " + monster.getBeschrijving());
                            System.out.println("Het monster valt aan!");
                            speler.attacked();
                            monster.verliesLeven();

                            if (speler.getLives() <= 0) {
                                System.out.println("Je hebt geen levens meer. Game over.");
                                return;
                            }

                            if (monster.isVerslagen()) {
                                System.out.println("Je hebt het monster verslagen!");
                                correct = true;
                            } else {
                                System.out.println("Het monster leeft nog! Probeer het opnieuw.");
                            }
                        }

                    }
                }

                Kamer volgende = db.getKamerById(huidigeKamer.getKamerId() + 1);
                if (volgende != null) {
                    speler.moveTo(volgende);
                    speler.saveToDatabase();
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
                update.updateVoortgang(speler.getNaam(), huidigeKamer.getKamerId());

                if (vorige != null){
                    speler.moveTo(vorige);
                    speler.saveToDatabase();
                }else {
                    System.out.println("Geen vorige kamer.");
                }
            } else if (input.equalsIgnoreCase("d")) {
                Kamer volgende = db.getKamerById(huidigeKamer.getKamerId() + 1);
                update.updateVoortgang(speler.getNaam(), huidigeKamer.getKamerId());
                if (volgende != null) {
                    speler.moveTo(volgende);
                    speler.saveToDatabase();
                }
                else System.out.println("Je bent bij de laatste kamer.");
            } else {
                System.out.println("Ongeldige invoer.");
            }
        }
    }
}