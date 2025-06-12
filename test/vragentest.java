import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class VragenTest {

    public interface Monster {
        void monsterTonen();
    }


    class MockMonster implements Monster {
        boolean getoond = false;

        @Override
        public void monsterTonen() {
            getoond = true;
        }

        public boolean isGetoond() {
            return getoond;
        }
    }


    class StubStrategieJuist implements VraagStrategie {
        @Override
        public boolean checkAnswer(ArrayList<String> antwoord) {
            return true;
        }
    }

    class StubStrategieFout implements VraagStrategie {
        @Override
        public boolean checkAnswer(ArrayList<String> antwoord) {
            return false;
        }
    }


    @Test
    void testCheckAnswer_FoutAntwoord_ZonderMonster() {
        StubStrategieFout strategie = new StubStrategieFout();

        Vragen vraag = new Vragen(3, "Wanneer is de daily scrum?", strategie, "Uitleg");

        ArrayList<String> antwoord = new ArrayList<>();
        antwoord.add("Nooit");

        boolean resultaat = vraag.checkAnswer(antwoord);
        assertFalse(resultaat);

    }

    void setMonster(Vragen vraag, Monster monster) {
        try {
            var field = Vragen.class.getDeclaredField("monsterTrigger");
            field.setAccessible(true);
            field.set(vraag, monster);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

