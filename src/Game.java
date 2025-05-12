import java.util.ArrayList;

public class Game {
    private Speler speler;
    private boolean gameOver = false;
    public static ArrayList<Speler> speler = new ArrayList<>();
    private int currentKamer;

    public Game(Speler speler) {
        this.speler = speler;
    }

    public void startGame() {
       System.out.println("Je staat nu in kamer " + speler.currentKamer());
    }

    public void toonStatus() {
        System.out.println("Speler: " + speler.getNaam());
        System.out.println("Levens: " + speler.getLives());
        //System.out.println("Huidige kamer: " + spelercurrentKamer());
    }

    public void stopGame() {
        System.out.println("Bedankt voor het spelen!");
        gameOver = true;
    }

    public boolean GameOver() {
        return gameOver;
    }
}
