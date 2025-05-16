import java.util.ArrayList;

public class Vragen {
    private String vraag;
    private Monster monsterTrigger;
    private VraagStrategie strategie;

    public Vragen(String vraag, VraagStrategie strategie, Monster monsterTrigger) {
        this.vraag = vraag;
        this.strategie = strategie;
        this.monsterTrigger = monsterTrigger;
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
}





