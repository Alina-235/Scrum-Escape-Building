import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class vragentest {

    @Test
    void testJuistAntwoordGeeftTrue() {
        VraagStrategie mockStrategie = mock(VraagStrategie.class);
        Monster mockMonster = mock(Monster.class);

        ArrayList<String> antwoord = new ArrayList<>();
        antwoord.add("Daily Scrum");

        when(mockStrategie.checkAnswer(antwoord)).thenReturn(true);

        Vragen vraag = new Vragen("Wat doen we dagelijks?", mockStrategie, mockMonster);
        boolean resultaat = vraag.checkAnswer(antwoord);

        assertTrue(resultaat);
        verify(mockMonster, never()).monsterTonen(); // monster mag niet getoond worden
    }

    @Test
    void testFoutAntwoordGeeftFalseEnToontMonster() {
        VraagStrategie mockStrategie = mock(VraagStrategie.class);
        Monster mockMonster = mock(Monster.class);

        ArrayList<String> antwoord = new ArrayList<>();
        antwoord.add("Lunch");

        when(mockStrategie.checkAnswer(antwoord)).thenReturn(false);

        Vragen vraag = new Vragen("Wat doen we dagelijks?", mockStrategie, mockMonster);
        boolean resultaat = vraag.checkAnswer(antwoord);

        assertFalse(resultaat);
        verify(mockMonster, times(1)).monsterTonen(); // monster moet getoond worden
    }

    @Test
    void testFoutAntwoordZonderMonsterGeeftFalse() {
        VraagStrategie mockStrategie = mock(VraagStrategie.class);

        ArrayList<String> antwoord = new ArrayList<>();
        antwoord.add("Lunch");

        when(mockStrategie.checkAnswer(antwoord)).thenReturn(false);

        Vragen vraag = new Vragen("Wat doen we dagelijks?", mockStrategie, null);
        boolean resultaat = vraag.checkAnswer(antwoord);

        assertFalse(resultaat);
        // geen monster om te tonen, dus niets te verifiëren
    }
}

