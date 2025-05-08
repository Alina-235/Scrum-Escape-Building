import java.util.Scanner;
public class Speler {
    private String naam;
    private int lives = 3;
    private int huidigeKamer = 0;
    private boolean ingelogd = false;

    public void inloggen() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Voer gebruikersnaam in: ");
        naam = scanner.nextLine();

        ingelogd = true;
        System.out.println("Welkom, " + naam + "!");
    }

    public boolean isIngelogd() {
        return ingelogd;
    }

    public String getNaam() {
        return naam;
    }

    public int getLives() {
        return lives;
    }

    public void verliesLeven() {
        lives--;
    }

    public int getHuidigeKamer() {
        return huidigeKamer;
    }

    public void setHuidigeKamer(int kamer) {
        huidigeKamer = kamer;
    }
}
