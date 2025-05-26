import org.junit.jupiter.api.Test;
import utn.services.spamDetector.impl.SpamDetector;

import static org.junit.jupiter.api.Assertions.*;

class SpamDetectorTest {

    private final SpamDetector detector = new SpamDetector();

    @Test
    void testEsSpamPorFraseVaga() {
        assertTrue(detector.esSpam("error grave"));
    }

    @Test
    void testNoEsSpamMensajeValido() {
        assertFalse(detector.esSpam("Considero que este hecho es incorrecto por las siguientes razones..."));
    }

    @Test
    void testEsSpamPorMensajeCorto() {
        assertTrue(detector.esSpam("No sirve"));
    }

    @Test
    void testEsSpamPorExclamaciones() {
        assertTrue(detector.esSpam("Esto está mal!!!"));
    }
}