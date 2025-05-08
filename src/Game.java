public class Game {
    private Speler speler;
    private boolean gameOver = false;

    public Game(Speler speler) {
        this.speler = speler;
    }

    public void startSpel() {
        System.out.println("Je staat nu in kamer " + speler.getHuidigeKamer());
    }

    public void toonStatus() {
        System.out.println("Speler: " + speler.getNaam());
        System.out.println("Levens: " + speler.getLives());
        System.out.println("Huidige kamer: " + speler.getHuidigeKamer());
    }

    public void stopSpel() {
        System.out.println("Bedankt voor het spelen!");
        gameOver = true;
    }

    public boolean GameOver() {
        return gameOver;
    }
}
