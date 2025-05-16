import java.util.ArrayList;

abstract class Kamer {
    protected int kamerId;
    protected String naam;
    protected String beschrijving;
    protected String doel;
    protected boolean passed = false;
    ArrayList<Vragen> vragen = new ArrayList<>();

    public Kamer(int kamerId, String naam, String beschrijving, String doel) {
        this.kamerId = kamerId;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.doel = doel;

        databaseSelect db = new databaseSelect();
        this.vragen = db.getVragenVoorKamer(kamerId);
    }

    public final void Room() {
        kamerNaam();
        kamerbeschrijving();
        kamerDoel();
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getDoel() {
        return doel;
    }

    protected abstract void kamerNaam();
    protected abstract void kamerbeschrijving();
    protected abstract void kamerDoel();
}
