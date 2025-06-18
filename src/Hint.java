import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public interface Hint {
    String getHint();
}

class HelpHint implements Hint {
    private final String tekst;

    public HelpHint(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public String getHint() {
        return tekst;
    }
}

class FunnyHint implements Hint {
    private final String tekst;

    public FunnyHint(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public String getHint() {
        return tekst;
    }
}


interface Joker {
    void gebruik(Speler speler);
    boolean beschikbaarIn(Kamer kamer);
}

interface HintRepository {
    List<Hint> getHints();
}

class DatabaseHintRepository implements HintRepository {
    @Override
    public List<Hint> getHints() {
        databaseSelect db = new databaseSelect();
        return db.getHints();
    }
}

class HintJoker implements Joker {
    private final Random random = new Random();
    private final List<Hint> hints;

    public HintJoker(HintRepository repository) {
        this.hints = repository.getHints();
    }

    @Override
    public void gebruik(Speler speler) {
        if (hints.isEmpty()) {
            System.out.println("Geen hints beschikbaar in de database.");
            return;
        }

        Hint hint = hints.get(random.nextInt(hints.size()));
        System.out.println("Hint: " + hint.getHint());
    }

    @Override
    public boolean beschikbaarIn(Kamer kamer) {
        return true;
    }
}


interface VragenRepository {
    List<Vragen> getVragenVoorKamer(int kamerId);
}


class DatabaseVragenRepository implements VragenRepository {
    private final databaseSelect db = new databaseSelect();

    @Override
    public List<Vragen> getVragenVoorKamer(int kamerId) {
        return db.getVragenVoorKamer(kamerId);
    }
}

class KeyJoker implements Joker {
    private int usesRemaining = 2;
    private final VragenRepository repository;

    public KeyJoker(VragenRepository repository) {
        this.repository = repository;
    }

    @Override
    public void gebruik(Speler speler) {
        if (usesRemaining <= 0) {
            System.out.println("Je hebt geen KeyJoker-gebruiken meer over.");
            return;
        }

        Vragen huidigeVraag = speler.getHuidigeVraag();
        if (huidigeVraag != null) {
            String correctAntwoord = huidigeVraag.getVraagStrategie().geefAntwoord();
            System.out.println("Correct antwoord voor de vraag: \"" + huidigeVraag.getVraagTekst() + "\" is: " + correctAntwoord);
        } else {
            System.out.println("Geen actieve vraag gevonden.");
        }

        usesRemaining--;

        if (!speler.getActieveMonsters().isEmpty()) {
            speler.losMonsterOp(speler.getActieveMonsters().get(0));
        }

        System.out.println("Je kunt de KeyJoker nog " + usesRemaining + " keer gebruiken.");
    }


    @Override
    public boolean beschikbaarIn(Kamer kamer) {
        return usesRemaining > 0;
    }
}






