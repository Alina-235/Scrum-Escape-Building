public class Monster {
    private String naam;
    private String beschrijving;
    private boolean verslagen;
    private int lives;


    public Monster(String naam,String beschrijving, boolean verslagen, int lives) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.verslagen = verslagen;
        this.lives = lives;
    }

    public String getNaam() {
        return naam;
    }

    public void valAan(Speler speler) {
        System.out.println("Het monster " + naam + " valt je aan!");

        speler.verliesLeven();
        System.out.println("Je verliest 1 leven. Nog " + speler.getLives() + " over.");
        //if ()
    }
}
