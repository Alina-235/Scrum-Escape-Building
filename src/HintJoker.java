import java.util.Random;

public class HintJoker implements Joker {
    private Random random = new Random();

    @Override
    public void gebruik(Speler speler) {
        Hint hint;
        if (random.nextBoolean()) {
            hint = new HelpHint();
        } else {
            hint = new FunnyHint();
        }
        System.out.println("Hint: " + hint.getHint());
    }

    @Override
    public boolean beschikbaarIn(Kamer kamer) {
        return true;
    }
}
