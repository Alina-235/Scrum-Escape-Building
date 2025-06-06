import java.util.List;
import java.util.Random;

class HintJoker implements Joker {
    private final Random random = new Random();
    private final List<Hint> hints;

    public HintJoker() {
        databaseSelect db = new databaseSelect();
        this.hints = db.getHints();
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
