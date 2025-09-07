package utn.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import utn.models.helpers.FuenteNombre;

import java.util.Map;
import java.util.Set;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "fuentes")
public class FuenteProvider {

    private Map<FuenteNombre, String> urls;

    public String getUrl(FuenteNombre fuente) {
        return urls.get(fuente);
    }

    public Set<FuenteNombre> getTodasLasFuentes() {
        return urls.keySet();
    }
}
