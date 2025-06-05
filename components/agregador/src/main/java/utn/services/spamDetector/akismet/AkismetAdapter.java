package utn.services.spamDetector.akismet;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import utn.services.spamDetector.ISpamDetector;

@Service
@Primary
public class AkismetAdapter implements ISpamDetector {

    private final AkismetService akismetService;
    private final UserContext userContext; // Ejemplo: para obtener IP y User-Agent

    public AkismetAdapter(AkismetService akismetService, UserContext userContext) {
        this.akismetService = akismetService;
        this.userContext = userContext;
    }

    @Override
    public boolean esSpam(String texto) {
        String ip = userContext.getUserIp();        // Obtener IP de quien hace la petición
        String userAgent = userContext.getUserAgent();  // Obtener User-Agent

        return akismetService.isSpam(texto, ip, userAgent);
    }
}