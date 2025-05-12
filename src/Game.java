import java.util.ArrayList;

public class Game {
    private boolean gameOver = false;
    private ArrayList<Speler> spelers;
    private ArrayList<Kamer> kamers;

    public Game() {
        this.spelers = new ArrayList<>();
        this.kamers = new ArrayList<>();
    }

    public void voegSpelerToe(Speler speler) {
        this.spelers.add(speler);
    }

    public ArrayList<Speler> getSpelers() {
        return spelers;
    }

    public void voegKamerToe(Kamer kamer) {
        this.kamers.add(kamer);
    }

    public ArrayList<Kamer> getKamers() {
        return kamers;
    }

    public void startGame() {
        if (spelers.isEmpty()) {
            System.out.println("Er zijn geen spelers om het spel te starten.");
            return;
        }

        for (Speler speler : spelers) {
            if (speler.getHuidigeKamer() != null) {
                System.out.println( speler.getNaam() + " staat nu in: " + speler.getHuidigeKamer().getNaam());
            } else {
                System.out.println("Speler " + speler.getNaam() + " heeft nog geen kamer.");
            }
        }
    }

    public void toonStatus() {
        for (Speler speler : spelers) {
            speler.toonStatus();
        }
    }

    public void stopGame() {
        System.out.println("Bedankt voor het spelen!");
        gameOver = true;
    }

    public void GameOver() {
        for (Speler speler : spelers) {
            if (speler.getLives() <= 0) {
                System.out.println("Speler " + speler.getNaam() + " is verslagen.");
                gameOver = true;
                break;
            }
        }
    }

}

