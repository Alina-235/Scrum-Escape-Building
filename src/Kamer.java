import java.util.ArrayList;

abstract class Kamer {
    protected String naam;
    protected int nummer;
    ArrayList <vragen> vragen = new ArrayList();
    protected boolean passed = false;

    public Kamer(String naam, int nummer) {
        this.naam = naam;
        this.nummer = nummer;
    }

    public String getNaam() {
        return naam;
    }
    public int getNummer() {
        return nummer;
    }

    public void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
    }
}

class KamerDailyScrum extends Kamer {
    public KamerDailyScrum(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    public void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
    }
}

class KamerPlanning extends Kamer {
    public KamerPlanning(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    public void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
    }
}

class KamerReview extends Kamer {
    public KamerReview(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    public void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
    }
}

class KamerScrumboard extends Kamer {
    public KamerScrumboard(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    public void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
    }
}

class KamerRetrospective extends Kamer {
    public KamerRetrospective(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    public void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
    }
}