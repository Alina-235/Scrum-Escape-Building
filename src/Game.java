import java.util.ArrayList;

class Game {
    private boolean gameOver = false;
    private ArrayList<Speler> spelers;
    private ArrayList<Kamer> kamers;
    private databaseSelect db = new databaseSelect();


    public Game(Speler speler) {
        this.spelers = new ArrayList<>();
        this.kamers = new ArrayList<>();
        this.spelers.add(speler);
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
            databaseSelect db = new databaseSelect();
            Kamer startKamer = db.getKamerById(0);

            if (startKamer == null) {
                System.out.println("Startkamer met ID 1 kon niet worden gevonden.");
                System.exit(0);
            }

            for (Speler speler : spelers) {
                if (speler.getHuidigeKamer() == null) {
                    speler.moveTo(startKamer);
                } else {
                    System.out.println(speler.getNaam() + " staat nu in: " + speler.getHuidigeKamer().getNaam());
                }
            }
            checkGameOver();
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

    public void checkGameOver() {
        for (Speler speler : spelers) {
            if (speler.getLives() <= 0) {
                System.out.println("Speler " + speler.getNaam() + " is verslagen.");
                gameOver = true;
                break;
            }
        }
    }

    public boolean GameOver() {
        return gameOver;
    }

    public void storyline(){
        System.out.println();
        System.out.println("Het is een vrijdag avond en jij bent alleen op kantoor. Iedereen is al naar huis, maar jij maakt nog de laatste taken af die op de planning staan. \n" +
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
