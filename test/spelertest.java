import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SpelerTest {

    static class DummyKamer extends Kamer {
        public DummyKamer() {
            super("Dummy kamer", 99);
        }
    }

    static class TestObserver implements SpelerObserver {
        String laatsteGebeurtenis = "";
        Speler laatstGemeld = null;

        @Override
        public void update(Speler speler, String gebeurtenis) {
            laatstGemeld = speler;
            laatsteGebeurtenis = gebeurtenis;
        }
    }

    static class TestMonster extends Monster {
        public TestMonster(String naam) {
            super(naam, "test", -1);
        }
    }

    @Test
    void testMoveTo_triggertObserver() {
        Speler speler = new Speler("TestSpeler");
        TestObserver observer = new TestObserver();
        speler.addObserver(observer);

        DummyKamer kamer = new DummyKamer();
        speler.moveTo(kamer);

        assertEquals("Nieuwe kamer", observer.laatsteGebeurtenis);
        assertEquals(speler, observer.laatstGemeld);
    }

    @Test
    void testAttacked_vermindertLeven_enTriggertObserver() {
        Speler speler = new Speler("TestSpeler");
        speler.setLives(3);
        TestObserver observer = new TestObserver();
        speler.addObserver(observer);

        int over = speler.attacked();

        assertEquals(2, over);
        assertEquals("Speler is aangevallen", observer.laatsteGebeurtenis);
        assertEquals(speler, observer.laatstGemeld);
    }

    @Test
    void testVoegMonstersToe_triggertObserver() {
        Speler speler = new Speler("TestSpeler");
        TestObserver observer = new TestObserver();
        speler.addObserver(observer);

        TestMonster monster = new TestMonster("TestMonster");
        speler.VoegMonstersToe(monster);

        assertTrue(speler.getActieveMonsters().contains(monster));
        assertEquals("Nieuwe monster verschenen", observer.laatsteGebeurtenis);
    }

    @Test
    void testLosMonsterOp_verwijdertMonster_enVerhoogtCounter() {
        Speler speler = new Speler("TestSpeler");
        TestObserver observer = new TestObserver();
        speler.addObserver(observer);

        TestMonster monster = new TestMonster("TestMonster");
        speler.VoegMonstersToe(monster);
        speler.losMonsterOp(monster);

        assertFalse(speler.getActieveMonsters().contains(monster));
        assertEquals(1, speler.getMonsterVerslagen());
        assertEquals("Monster is verslagen", observer.laatsteGebeurtenis);
    }
}

