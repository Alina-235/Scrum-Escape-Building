import java.util.ArrayList;

public class Speler {
    private String naam;
    private int lives;
    private int huidigeKamer;
    private ArrayList<Integer> opgelosteKamers;
    private ArrayList<Monster> huidigeMonsters;

    public Speler(String naam) {
        this.naam = naam;
        this.lives = 3;
        this.huidigeKamer = 1;
        this.opgelosteKamers = new ArrayList<>();
        this.huidigeMonsters = new ArrayList<>();
    }

    public String getNaam() {
        return naam;
    }

    public int getHuidigeKamer() {
        return huidigeKamer;
    }
    public void setHuidigeKamer(int Kamer) {
        this.huidigeKamer = Kamer;
    }

    public void voegOpgelosteKamerToe(int Kamer) {
        if (!opgelosteKamers.contains(Kamer)) {
            opgelosteKamers.add(Kamer);
        }
    }

    public void voegMonsterToe(Monster monster) {
        if (!huidigeMonsters.contains(monster)){
            huidigeMonsters.add(monster);
        }
    }

    public void losMonsterOp(Monster monster, String oplossing){
        if (monster.magVerdwijnen(oplossing)) {
            huidigeMonsters.remove(monster);
            System.out.println("Monster defeated!");
        } else {
            System.out.println("Verkeerde oplossing, monster blijft nog levend.");
            attacked();
        }
    }

    public void beantwoordVraag(Vraag vraag, String antwoord){
        if (vraag.isCorrect(antwoord)){
            System.out.println("Goed beantwoord!");
            voegOpgelosteKamerToe(huidigeKamer);
        } else {
            System.out.println("Fout antwoord!");
            voegMonsterToe(new Monster("Onjuist antwoord"));
            attacked();
        }
    }

    public void attacked(){
        lives--;
        System.out.println("Je bent aangevallen! Levens over:" + lives);
    }

    public boolean gameOver() {
        return lives <= 0;
    }

    public void getStatus() {
        System.out.println("Naam: " + naam);
        System.out.println("Levens: " + lives);
        System.out.println("Huidige Kamer: " + huidigeKamer);
        System.out.println("Opgeloste kamers: " + opgelosteKamers);
        System.out.println("Actieve monsters: ");
        for (Monster monster : huidigeMonsters) {
            System.out.print(monster.getNaam() + " ");
        }
        System.out.println();
    }
}
