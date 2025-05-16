import java.util.ArrayList;

public class Speler extends Character {
    private Kamer huidigeKamer;
    private int monsterVerslagen;
    private ArrayList<Monster> actieveMonsters;
    private ArrayList<SpelerObserver> observers = new ArrayList<>();

    public Speler(String naam, int characterID) {
        super(naam, "Scrum escape speler", 3, characterID);
        this.monsterVerslagen = 0;
        this.actieveMonsters = new ArrayList<>();
        this.huidigeKamer = null;
    }

    public void addObserver(SpelerObserver observer){
        observers.add(observer);
    }

    private void notifyObservers(String gebeurtenis){
        for (SpelerObserver observer: observers) {
            observer.update(this, gebeurtenis);
        }
    }

    public void moveTo(Kamer kamer) {
        this.huidigeKamer = kamer;
        System.out.println("Je bent nu in kamer: " + kamer.getNaam());
        notifyObservers("Nieuwe kamer:");
    }

    public int attacked() {
        lives--;
        System.out.println("Je bent attacked! Levens over: " + lives);
        if (lives <= 0) {
            verslagen = true;
        }
        notifyObservers("Speler is aangevallen");
        return lives;
    }

    public void VoegMonstersToe(Monster monster) {
        if (!actieveMonsters.contains(monster)) {
            actieveMonsters.add(monster);
        }
        notifyObservers("Nieuwe monster verschenen");
    }

    public void losMonsterOp(Monster monster) {
            actieveMonsters.remove(monster);
            monsterVerslagen++;
            System.out.println("Monster verslagen!");
            notifyObservers("Monster verslagen");
    }

    public void toonStatus() {
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

        public boolean isVerslagen() {
            return verslagen;
        }

        public boolean isIngelogd() {
            // tijdelijke login check
            return true;
        }
    }
