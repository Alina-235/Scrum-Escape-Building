import java.util.ArrayList;

abstract class Kamer {
    protected String naam;
    protected String beschrijving;
    protected String doel;
    protected ArrayList<Vragen> vragen = new ArrayList<>();
    protected boolean passed = false;
    protected int kamernummer;
    protected String boek;

    public Kamer(String naam, String beschrijving, String doel, int kamernummer, String boek) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.doel = doel;
        this.kamernummer = kamernummer;
        this.boek = boek;
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

    public int getKamerId() {
        return kamernummer;
    }

    public String getBoek() {
        return boek;
    }
    protected abstract void kamerNaam();

    protected abstract void kamerbeschrijving();

    protected abstract void kamerDoel();
}

class KamerStart extends Kamer {
    public KamerStart(String naam, String beschrijving, String doel, int kamernummer, String boek) {
        super(naam, beschrijving, doel, kamernummer, boek);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(getDoel());
    }
}


class KamerDailyScrum extends Kamer {
    public KamerDailyScrum(String naam, String beschrijving, String doel, int kamernummer, String boek) {
        super(naam, beschrijving, doel, kamernummer, boek);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(getDoel());
    }
}

class KamerPlanning extends Kamer {
    public KamerPlanning(String naam, String beschrijving, String doel, int kamernummer, String boek) {
        super(naam, beschrijving, doel, kamernummer, boek);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(getDoel());
    }
}

class KamerReview extends Kamer {
    public KamerReview(String naam, String beschrijving, String doel, int kamernummer, String boek) {
        super(naam, beschrijving, doel, kamernummer, boek);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(getDoel());
    }
}

class KamerScrumboard extends Kamer {
    public KamerScrumboard(String naam, String beschrijving, String doel, int kamernummer, String boek) {
        super(naam, beschrijving, doel, kamernummer, boek);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(getDoel());
    }
}

class KamerRetrospective extends Kamer {
    public KamerRetrospective(String naam, String beschrijving, String doel, int kamernummer, String boek) {
        super(naam, beschrijving, doel, kamernummer, boek);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(getDoel());
    }
}

class KamerFinal extends Kamer {

    public KamerFinal(String naam, String beschrijving, String doel, int kamernummer,String boek) {
        super(naam, beschrijving, doel, kamernummer, boek);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(getDoel());
    }
}

