package utn.services;

import org.springframework.stereotype.Component;
import utn.models.helpers.FuenteNombre;

import java.util.Map;
import java.util.Set;

@Component
public class FuenteProvider {

    private final Map<FuenteNombre, String> urls;

    public FuenteProvider() {
        urls = Map.of(
            FuenteNombre.ESTATICA, "http://localhost:8083/hechos",
            FuenteNombre.DINAMICA, "http://localhost:8082/hechos",
            FuenteNombre.PROXY,    "http://localhost:8084/hechos"
        );
    }

    public String getUrl(FuenteNombre fuente) {
        return urls.get(fuente);
    }

    public Set<FuenteNombre> getTodasLasFuentes() {
        return urls.keySet();
    }
}