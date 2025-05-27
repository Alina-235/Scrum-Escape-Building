import java.util.ArrayList;

class GameRepository {
    private databaseSelect db = new databaseSelect();

    public Kamer getStartKamer() {
        return db.getKamerById(2);
    }
}
public class GameController {
    private Game game;
    private GameStory story;

    public GameController(Speler speler) {
        this.game = new Game();
        this.game.voegSpelerToe(speler);
        this.story = new GameStory();
    }

    public boolean verwerkKeuze(int keuze) {
        switch (keuze) {
            case 1:
                story.toonIntro();
                game.startGame();
                return true;
            case 2:
                toonCredits();
                return true;
            case 3:
                System.out.println("Programma wordt afgesloten.");
                return false;
            default:
                System.out.println("Ongeldige keuze, probeer opnieuw.");
                return true;
        }
    }

    private void toonCredits() {
        System.out.println("\nEscape Scrum Building © 2025");
        System.out.println("Gemaakt door jouw projectteam.");
    }

    public boolean isGameOver() {
        return game.isGameOver();
    }
}

class GameStory {
    public void toonIntro() {
        System.out.println();
        System.out.println("Het is een vrijdag avond en jij bent alleen op kantoor. Iedereen is al naar huis, maar jij maakt nog de laatste taken af die op de planning staan. \n " +
                "Inmiddels is het al 22:00 ’s avonds. Je kijkt naar buiten en je ziet de maan schijnen door de ramen. \n" +
                "De auto’s staan voor het stoplicht te wachten op het groene licht.\n" +
                "\n" +
                "Je gaat weer terug aan het werk. Na een tijdje raak je opeens verdwaald in je gedachten en denk je terug naar dat moment toen je een gerucht hoorde van jouw collega. \n" +
                "“Probeer altijd voor middernacht hier weg te zijn. Ik heb gehoord dat er monsters, gemuteerde mensen, rondlopen na middernacht die super gevaarlijk zijn. \n" +
                "Niemand weet waar ze vandaan komen en hoe zij ontstaan zijn.” Je kijkt op naar de klok. Het is 23:47 uur. “Bijna middernacht.” denk je in jezelf. \n" +
                "\n" +
                "Je besluit om op te ruimen en naar huis te gaan. Je zet jouw laptop in je tas en neemt alle belangrijke documenten mee in jouw rugzak. \n" +
                "De deur is een paar stappen van jouw bureau vandaan. De deurklink is net binnen bereik wanneer je een hard geluid hoort vanuit het magazijn. \n" +
                "Je loopt richting het magazijn en je trekt de deur open. Het is donker. Het ruikt een beetje muf. Je zet een paar stappen binnen het magazijn.\n" +
                "\n" +
                "De deur valt achter je dicht. Je probeert met man en macht de deur open te krijgen, maar het lukt je niet. " +
                "\nDe deur is op slot. “Shit,” fluister je terwijl je paniekerig rondkijkt. “Hoe kom ik hier in godsnaam uit?”\n");

    }
}
class SpelerManager {
    private ArrayList<Speler> spelers = new ArrayList<>();

    public void voegSpelerToe(Speler speler) {
        spelers.add(speler);
    }

    public ArrayList<Speler> getSpelers() {
        return spelers;
    }

    public boolean heeftSpelers() {
        return !spelers.isEmpty();
    }

    public void toonStatus() {
        for (Speler s : spelers) {
            s.toonStatus();
        }
    }

    public boolean isGameOver() {
        for (Speler s : spelers) {
            if (s.getLives() <= 0) return true;
        }
        return false;
    }

    public void plaatsSpelersInKamer(Kamer kamer) {
        for (Speler s : spelers) {
            if (s.getHuidigeKamer() == null) {
                s.moveTo(kamer);
                System.out.println("Speler " + s.getNaam() + " is geplaatst in startkamer: " + kamer.getNaam());
            } else {
                System.out.println(s.getNaam() + " staat nu in: " + s.getHuidigeKamer().getNaam());
            }
        }
    }
}
public class Game {
    private boolean gameOver = false;
    private SpelerManager spelerManager = new SpelerManager();
    private ArrayList<Kamer> kamers = new ArrayList<>();
    private GameRepository repo = new GameRepository();

    public void voegSpelerToe(Speler speler) {
        spelerManager.voegSpelerToe(speler);
    }

    public void voegKamerToe(Kamer kamer) {
        kamers.add(kamer);
    }

    public void startGame() {
        if (!spelerManager.heeftSpelers()) {
            System.out.println("Er zijn geen spelers om het spel te starten.");
            return;
        }

        Kamer startKamer = repo.getStartKamer();

        if (startKamer == null) {
            System.out.println("Startkamer met ID 2 kon niet worden gevonden.");
            return;
        }

        spelerManager.plaatsSpelersInKamer(startKamer);

        checkGameOver();
    }

    public void toonStatus() {
        spelerManager.toonStatus();
    }

    public void stopGame() {
        System.out.println("Bedankt voor het spelen!");
        gameOver = true;
    }

    public void checkGameOver() {
        if (spelerManager.isGameOver()) {
            System.out.println("Een speler is verslagen. Game over.");
            gameOver = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}

