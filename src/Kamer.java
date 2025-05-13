import java.util.ArrayList;

abstract class Kamer {
    protected String naam;
    protected int nummer;
    ArrayList <Vragen> vragen = new ArrayList();
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

    protected abstract void ToonInhoud ();
}

class KamerDailyScrum extends Kamer {
    public KamerDailyScrum(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    protected void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
        System.out.println("Hier vind je alles gerelateerd aan de dagelijkse scrum.");
        System.out.println();
        System.out.println("vind de hendel om de deur te openen");

    }
}

class KamerPlanning extends Kamer {
    public KamerPlanning(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    protected void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
        System.out.println("Hier vind je alles gerelateerd aan de scrum planning.");
        System.out.println();
        System.out.println("Zoek de code van de deur om naar de volgende kamer te gaan.");
    }
}

class KamerReview extends Kamer {
    public KamerReview(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    protected void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
        System.out.println("Hier vind je alles gerelateerd aan de review.");
        System.out.println();
        System.out.println("beantwoord de vragen om naar de volgende kamer te gaan.");
    }
}

class KamerScrumboard extends Kamer {
    public KamerScrumboard(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    protected void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
        System.out.println("Hier vind je alles gerelateerd aan het scrumboard.");
        System.out.println();
        System.out.println("zoek de deur.");
    }
}

class KamerRetrospective extends Kamer {
    public KamerRetrospective(String naam, int nummer) {
        super(naam, nummer);
    }

    @Override
    protected void ToonInhoud() {
        System.out.println("Welkom in kamer " + naam);
        System.out.println("Hier vind je alles gerelateerd aan het retrospective.");
        System.out.println();
        System.out.println("beantwoord de vraag om door te gaan.");
    }
}