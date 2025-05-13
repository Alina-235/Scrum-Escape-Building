import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<String, String> juisteKoppelingen;

    public PuzzelStrategie(HashMap<String, String> juisteKoppelingen) {
        this.juisteKoppelingen = juisteKoppelingen;
    }

    @Override
    public boolean checkAnswer(ArrayList<String> antwoord) {
        for (String koppeling : antwoord) {
            String[] delen = koppeling.split("=");
            if (delen.length != 2) return false;

            String term = delen[0].trim();
            String definitie = delen[1].trim();

            if (!juisteKoppelingen.containsKey(term) ||
                    !juisteKoppelingen.get(term).equalsIgnoreCase(definitie)) {
                return false;
            }
        }
        return true;
    }
}





