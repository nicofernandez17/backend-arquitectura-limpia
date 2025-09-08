package utn.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AgregadorClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8081";

    public AgregadorClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<ColeccionDTO> obtenerColecciones() {
        String url = baseUrl + "/admin/colecciones";

        try {
            ResponseEntity<ColeccionDTO[]> response = restTemplate.getForEntity(url, ColeccionDTO[].class);
            return response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (RestClientException e) {
            System.err.println("Error consultando " + url + ": " + e.getMessage());
            return List.of();
        }
    }
}
