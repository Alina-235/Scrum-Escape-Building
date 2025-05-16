import java.util.ArrayList;

abstract class Kamer {
    protected String naam;
    protected String beschrijving;
    protected String doel;
    ArrayList <vragen> vragen = new ArrayList();
    protected boolean passed = false;


    public Kamer(String naam, String beschrijving, String doel ) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.doel = doel;
    }

    public final void Room () {
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
    public String getDoel (){
        return doel;
    }

    protected abstract void kamerNaam();
    protected abstract void kamerbeschrijving();
    protected abstract void kamerDoel();
}

class KamerDailyScrum extends Kamer {

    public KamerDailyScrum(String naam, String beschrijving, String doel) {
        super(naam, beschrijving, doel);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(super.getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(super.getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(super.getDoel());
    }
}

class KamerPlanning extends Kamer {
    public KamerPlanning(String naam, String beschrijving, String doel) {
        super(naam, beschrijving, doel);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(super.getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(super.getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(super.getDoel());
    }
}

class KamerReview extends Kamer {
    public KamerReview(String naam, String beschrijving, String doel) {
        super(naam, beschrijving, doel);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(super.getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(super.getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(super.getDoel());
    }
}

class KamerScrumboard extends Kamer {
    public KamerScrumboard(String naam, String beschrijving, String doel) {
        super(naam, beschrijving, doel);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(super.getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(super.getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(super.getDoel());
    }
}

class KamerRetrospective extends Kamer {
    public KamerRetrospective(String naam, String beschrijving, String doel) {
        super(naam, beschrijving, doel);
    }

    @Override
    protected void kamerNaam() {
        System.out.println(super.getNaam());
    }

    @Override
    protected void kamerbeschrijving() {
        System.out.println(super.getBeschrijving());
    }

    @Override
    protected void kamerDoel() {
        System.out.println(super.getDoel());
    }
}