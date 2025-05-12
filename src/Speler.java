import java.util.ArrayList;

public class Speler extends Character{
    private Kamer huidigeKamer;
    private int monsterVerslagen;
    private ArrayList<Monster> actieveMonsters;

    public Speler(String naam, int characterID) {
        super(naam, "Scrum escape speler", 3, characterID);
        this.monsterVerslagen = 0;
        this.actieveMonsters = new ArrayList<>();
        this.huidigeKamer = null;
    }

    public void moveTo(Kamer kamer) {
        this.huidigeKamer = kamer;
        System.out.println("Je bent nu in kamer: " + kamer.getNaam());
    }

    public int attacked(){
        lives--;
        System.out.println("Je bent attacked! Levens over: " + lives);
        if (lives <= 0) {
            verslagen = true;
        }
        return lives;
    }

    public void VoegMonstersToe(Monster monster){
        if (!actieveMonsters.contains(monster)) {
            actieveMonsters.add(monster);
        }
    }

    public void losMonsterOp(Monster monster, String oplossing){
        if (monster.magVerdwijnen(oplossing)){
            actieveMonsters.remove(monster);
            monsterVerslagen++;
            System.out.println("Monster verslagen!");
        } else {
            System.out.println("Verkeerde antwoord, monster blijft levend.");
            attacked();
        }
    }

    public void toonStatus(){
        System.out.println("Naam: " + naam);
        System.out.println("ID: " + characterID);
        System.out.println("Kamer: " + huidigeKamer);
        System.out.println("Levens: " + lives);
        System.out.println("Monsters verslagen: " + monsterVerslagen);
        System.out.println("Actieve monsters: " + actieveMonsters);
        for (Monster monster : actieveMonsters) {
            System.out.print(monster.getNaam() + " ");
        }
        System.out.println();
    }

    public Kamer getHuidigeKamer() {
        return huidigeKamer;
    }
    public int getMonsterVerslagen() {
        return monsterVerslagen;
    }
    public ArrayList<Monster> getActiveMonsters() {
        return actieveMonsters;
    }

}
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
            System.out.println("Kamer " + Kamer + " is opgelost!");
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
        if (vraag.isCorrect(antwoord)) {
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

    public ArrayList<Monster> getActieveMonsters(){
        return huidigeMonsters;
    }

    public ArrayList<Integer> getOpgelosteKamers() {
        return opgelosteKamers;
    }

    public boolean isKamerOpgelost(int kamer){
        return opgelosteKamers.contains(kamer);
    }
}
