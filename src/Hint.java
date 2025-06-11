import java.util.List;
import java.util.Random;

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

class KeyJoker implements Joker {
    private int usesRemaining = 2;

    @Override
    public void gebruik(Speler speler) {
        if (usesRemaining > 0) {
            System.out.println("Je hebt een sleutel gebruikt om een monster te ontwijken!");
            usesRemaining--;

            if (!speler.getActieveMonsters().isEmpty()) {
                speler.losMonsterOp(speler.getActieveMonsters().get(0));
            }

            System.out.println("Je kunt de KeyJoker nog " + usesRemaining + " keer gebruiken.");
        } else {
            System.out.println("Je hebt geen KeyJoker-gebruiken meer over.");
        }
    }

    @Override
    public boolean beschikbaarIn(Kamer kamer) {
        return usesRemaining > 0;
    }
}



