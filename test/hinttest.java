import org.junit.jupiter.api.Test;
import java.util.List;

class HintJokerTest {

    @Test
    void testHintJokerMetStubGeeftAltijdZelfdeHint() {

        HintRepository stubRepository = new HintRepositoryStub();
        HintJoker joker = new HintJoker(stubRepository);
        Speler speler = new Speler("Tester");


        joker.gebruik(speler);

    }

    static class HintRepositoryStub implements HintRepository {
        @Override
        public List<Hint> getHints() {
            return List.of(new HelpHint("Dit is een testhint!"));
        }
    }

}

