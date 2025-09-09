package utn.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import utn.model.domain.Coleccion;
import utn.model.domain.Hecho;
import utn.model.dto.ColeccionDTO;
import utn.model.dto.ColeccionMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgregadorClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8081";

    public AgregadorClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * Obtiene todas las colecciones desde el agregador y las convierte a dominio.
     */
    public List<Coleccion> obtenerColecciones() {
        List<ColeccionDTO> coleccionesDTO = obtenerColeccionesDTO();

        return coleccionesDTO.stream()
                .map(ColeccionMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los hechos de todas las colecciones en dominio.
     */
    public List<Hecho> obtenerTodosLosHechos() {
        return obtenerColecciones().stream()
                .flatMap(coleccion -> coleccion.getHechos().stream())
                .collect(Collectors.toList());
    }

    /**
     * Méthod privado que solo obtiene los DTOs del agregador.
     */
    private List<ColeccionDTO> obtenerColeccionesDTO() {
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
