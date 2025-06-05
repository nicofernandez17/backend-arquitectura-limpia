package utn.services.spamDetector.local;

import org.springframework.stereotype.Service;
import utn.services.spamDetector.ISpamDetector;

import java.util.Arrays;
import java.util.List;

@Service
public class SpamDetector implements ISpamDetector {
    private final List<String> vaguePhrases = Arrays.asList(
            "eliminen esto", "borren esto", "esto está mal",
            "quiten esto", "esto es un error", "no sirve",
            "borrar ya", "esto no debería estar", "error grave"
    );

    private final List<String> emotionalWords = Arrays.asList(
            "urgente", "inaceptable", "ridículo", "terrible", "basta", "mentira"
    );

    @Override
    public boolean esSpam(String message) {
        if (message == null || message.isBlank()) return true;

        String msg = message.toLowerCase();
        int score = 0;

        // Frases vagas y repetidas
        for (String phrase : vaguePhrases) {
            if (msg.contains(phrase)) score += 2;
        }

        // Lenguaje emocional
        for (String word : emotionalWords) {
            if (msg.contains(word)) score += 1;
        }

        // Mensaje demasiado corto (poca justificación)
        if (msg.length() < 20) score += 2;

        // Demasiado largo sin justificar (texto genérico largo)
        if (msg.length() > 500 && vaguePhrases.stream().anyMatch(msg::contains)) score += 1;

        // Exclamaciones excesivas
        long exclamations = msg.chars().filter(c -> c == '!').count();
        if (exclamations > 2) score += 1;

        // Umbral para marcar como spam
        return score >= 4;
    }
}
