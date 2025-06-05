package utn.services.spamDetector.akismet;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class UserContext {

    private final HttpServletRequest request;

    public UserContext(HttpServletRequest request) {
        this.request = request;
    }

    public String getUserIp() {
        // Para obtener la IP real, puede haber proxies, revisamos header X-Forwarded-For
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        } else {
            // En X-Forwarded-For puede haber lista de IPs separadas por coma
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

    public String getUserAgent() {
        return request.getHeader("User-Agent");
    }
}