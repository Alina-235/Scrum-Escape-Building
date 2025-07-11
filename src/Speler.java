import java.util.ArrayList;
import java.util.Scanner;

interface SpelerObserver {
    void update(Speler speler, String gebeurtenis);
}

class Speler extends Character {
    private Kamer huidigeKamer;
    private int monsterVerslagen;
    private ArrayList<Monster> actieveMonsters;
    private ArrayList<SpelerObserver> observers = new ArrayList<>();
    ArrayList<Speler> alleSpelers = new ArrayList<>();
    private Joker joker;
    databaseInsert insert = new databaseInsert();
    static Scanner scanner = new Scanner(System.in);


    private ObserverActies observerActies = new ObserverActies();
    private MonsterActies monsterActies = new MonsterActies();

    public Speler(String naam, int characterID) {
        super(naam, "Scrum escape speler", 3, characterID);
        this.monsterVerslagen = 0;
        this.actieveMonsters = new ArrayList<>();
        this.huidigeKamer = null;
    }

    public void addObserver(SpelerObserver observer){
        observerActies.addObserver(observer);
    }

    public void moveTo(Kamer kamer) {
        this.huidigeKamer = kamer;
        System.out.println("Je bent nu in kamer: " + kamer.getNaam());
        observerActies.notifyObservers(this, "Nieuwe kamer");
    }

    public int attacked() {
        lives--;
        System.out.println("Je bent attacked! Levens over: " + lives);
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
        observerActies.notifyObservers(this,"Monster is verslagen");
    }

    public void toonStatus() {
        System.out.println("Naam: " + naam);
        System.out.println("ID: " + characterID);
        System.out.println("Kamer: " + huidigeKamer);
        System.out.println("Levens: " + lives);
        System.out.println("Monsters verslagen: " + monsterVerslagen);
        System.out.println("Actieve monsters: " + actieveMonsters);

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

    public void spelerToevoegen() {
        int i = 0;
        for (Speler gebruiker : alleSpelers) {
            i++;
        }
        System.out.println("Jouw naam is: ");
        String naam = scanner.nextLine();
        Speler speler = new Speler(naam, );
        alleSpelers.add(speler);
        insert.InsertCharacter(naam,  "speler");
    }

    public String getNaam(){
        return naam;
    }

    public int getLives(){
        return lives;

    }
}
