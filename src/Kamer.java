public abstract class Kamer {
    protected String naam;
    protected String beschrijving;
    protected Monster monster;

    public Kamer(String naam, String beschrijving, Monster monster) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.monster = monster;
    }

    public abstract void betreed(Speler speler);

    public String getNaam() {
        return naam;
    }

    public Monster getMonster() {
        return monster;
    }
}
