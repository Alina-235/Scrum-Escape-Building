import java.util.ArrayList;

interface SpelerObserver {
    void update(Speler speler, String gebeurtenis);
}

public class Speler extends Character {
    private Kamer huidigeKamer;
    private int monsterVerslagen;
    private ArrayList<Monster> actieveMonsters;
    private ArrayList<SpelerObserver> observers = new ArrayList<>();
    private Joker joker;

    private ObserverActies observerActies = new ObserverActies();
    private MonsterActies monsterActies = new MonsterActies();

    public Speler(String naam) {
        super(naam, "Scrum escape speler", -1, 3);
        this.monsterVerslagen = 0;
        this.actieveMonsters = new ArrayList<>();
        this.huidigeKamer = null;
    }

    public Speler(String naam, int characterID) {
        super(naam, "Scrum escape speler", characterID, 3);
        this.monsterVerslagen = 0;
        this.actieveMonsters = new ArrayList<>();
        this.huidigeKamer = null;
    }

    public void addObserver(SpelerObserver observer) {
        observerActies.addObserver(observer);
    }

    public void moveTo(Kamer kamer) {
        this.huidigeKamer = kamer;
        System.out.println("Je bent nu in kamer: " + kamer.getNaam());
        observerActies.notifyObservers(this, "Nieuwe kamer");
    }

    public int attacked() {
        lives--;
        System.out.println("Je bent aangevallen! Levens over: " + lives);
        if (lives <= 0) {
            verslagen = true;
        }
        observerActies.notifyObservers(this, "Speler is aangevallen");
        return lives;
    }

    public void VoegMonstersToe(Monster monster) {
        if (!actieveMonsters.contains(monster)) {
            actieveMonsters.add(monster);
        }
        observerActies.notifyObservers(this, "Nieuwe monster verschenen");
    }

    public void losMonsterOp(Monster monster) {
        actieveMonsters.remove(monster);
        monsterVerslagen++;
        System.out.println("Monster is verslagen!");
        observerActies.notifyObservers(this, "Monster is verslagen");
    }

    public void toonStatus() {
        System.out.println("Naam: " + naam);
        System.out.println("ID: " + characterID);
        System.out.println("Kamer: " + (huidigeKamer != null ? huidigeKamer.getNaam() : "Geen"));
        System.out.println("Levens: " + lives);
        System.out.println("Monsters verslagen: " + monsterVerslagen);
        System.out.println("Actieve monsters: " + actieveMonsters.size());
    }


    public Kamer getHuidigeKamer() {
        return huidigeKamer;
    }

    public int getMonsterVerslagen() {
        return monsterVerslagen;
    }

    public ArrayList<Monster> getActieveMonsters() {
        return actieveMonsters;
    }

    public boolean isVerslagen() {
        return verslagen;
    }

    public boolean isIngelogd() {
        return true;
    }

    public void kiesJoker(Joker joker) {
        this.joker = joker;
        System.out.println("Je hebt gekozen voor een " + joker.getClass().getSimpleName());
    }

    public void gebruikJoker(Kamer kamer) {
        if (joker != null && joker.beschikbaarIn(kamer)) {
            joker.gebruik(this);
        } else {
            System.out.println("Joker is hier niet beschikbaar of niet gekozen.");
        }
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public void saveToDatabase() {
        databaseSelect dbSelect = new databaseSelect();
        databaseInsert dbInsert = new databaseInsert();

        if (this.characterID <= 0) {
            int newId = dbInsert.insertNewGameCharacter(
                    this.naam,
                    this.beschrijving,
                    this.lives,
                    huidigeKamer != null ? huidigeKamer.getKamerId() : 1,
                    "speler"
            );
            this.setCharacterID(newId);
            System.out.println("Nieuwe speler opgeslagen met ID: " + newId);
        } else {
            dbInsert.updateGameCharacter(
                    this.characterID,
                    this.naam,
                    this.beschrijving,
                    this.lives,
                    huidigeKamer != null ? huidigeKamer.getKamerId() : 1,
                    "speler"
            );
            System.out.println("Speler bijgewerkt in database.");
        }
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getNaam() {
        return naam;
    }

    public int getLives() {
        return lives;
    }

    public void setHuidigeKamer(Kamer kamer) {
        this.huidigeKamer = kamer;
    }
}
