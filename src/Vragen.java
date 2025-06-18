import java.util.ArrayList;

class Vragen {
    private String vraag;
    private Monster monsterTrigger;
    private VraagStrategie strategie;
    private int vraagId;
    private String uitleg;

    public Vragen(int vraagId, String vraag, VraagStrategie strategie, String uitleg) {
        this.vraagId = vraagId;
        this.vraag = vraag;
        this.strategie = strategie;
        this.uitleg = uitleg;
    }

    public VraagStrategie getVraagStrategie() {
        return strategie;
    }

    public String getVraagTekst() {
        return vraag;
    }

    public int getVraagId() {
        return vraagId;
    }

    public String getUitleg() {
        return uitleg;
    }

    public String getVraag() {
        return vraag;
    }

    public boolean checkAnswer(ArrayList<String> antwoord) {
        boolean juist = strategie.checkAnswer(antwoord);
        if (!juist && monsterTrigger != null) {
            monsterTrigger.monsterTonen();
        }
        return juist;
    }
}

interface VraagStrategie {
    boolean checkAnswer(ArrayList<String> antwoord);

    default String geefAntwoord() {
        return "Niet beschikbaar";
    }

}

class MeerkeuzeStrategie implements VraagStrategie {
    private String juistAntwoord;

    public MeerkeuzeStrategie(String juistAntwoord) {
        this.juistAntwoord = juistAntwoord;
    }

    @Override
    public boolean checkAnswer(ArrayList<String> antwoord) {
        return antwoord.size() == 1 && juistAntwoord.equalsIgnoreCase(antwoord.get(0));
    }

    @Override
    public String geefAntwoord() {
        return juistAntwoord;
    }

}

class OpenInvulStrategie implements VraagStrategie {
    private String juistAntwoord;

    public OpenInvulStrategie(String juistAntwoord) {
        this.juistAntwoord = juistAntwoord;
    }

    @Override
    public boolean checkAnswer(ArrayList<String> antwoord) {
        return antwoord.size() == 1 && juistAntwoord.equalsIgnoreCase(antwoord.get(0).trim());
    }

    @Override
    public String geefAntwoord() {
        return juistAntwoord;
    }
}


class PuzzelStrategie implements VraagStrategie {
    private String juistAntwoord;

    public PuzzelStrategie(String juistAntwoord) {
        this.juistAntwoord = juistAntwoord;
    }

    @Override
    public boolean checkAnswer(ArrayList<String> antwoord) {
        return antwoord.size() == 1 && juistAntwoord.equalsIgnoreCase(antwoord.get(0).trim());
    }

    @Override
    public String geefAntwoord() {
        return juistAntwoord;
    }

}
