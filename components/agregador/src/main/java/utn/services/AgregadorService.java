package utn.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.configs.FuenteConfig;
import utn.models.domain.Hecho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AgregadorService {

    private final RestTemplate restTemplate;
    private final FuenteConfig fuenteConfig;

    public AgregadorService(RestTemplateBuilder builder, FuenteConfig fuenteConfig) {
        this.restTemplate = builder.build();
        this.fuenteConfig = fuenteConfig;
    }

    public List<Hecho> obtenerTodosLosHechos() {
        List<Hecho> hechos = new ArrayList<>();
        for (String url : fuenteConfig.getUrls()) {
            hechos.addAll(obtenerHechosDesdeUrl(url));
        }
        return hechos;
    }

    private List<Hecho> obtenerHechosDesdeUrl(String url) {
        ResponseEntity<List<Hecho>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Hecho>>() {}
        );
        return response.getBody() != null ? response.getBody() : Collections.emptyList();
    }
}