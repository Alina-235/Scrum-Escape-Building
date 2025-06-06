import java.util.Arrays;
import java.util.List;

public class KeyJoker implements Joker {
    private List<String> toegestaneKamers = Arrays.asList("KamerPlanning", "KamerReview");

    @Override
    public void gebruik(Speler speler) {
        System.out.println("Je hebt een sleutel gebruikt om een monster te ontwijken!");
        if (!speler.getActiveMonsters().isEmpty()) {
            speler.losMonsterOp(speler.getActiveMonsters().get(0));
        } else {
            System.out.println("Geen actieve monsters om op te lossen.");
        }
    }

    @Override
    public boolean beschikbaarIn(Kamer kamer) {
        return toegestaneKamers.contains(kamer.getClass().getSimpleName());
    }
}

