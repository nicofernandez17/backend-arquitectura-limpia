package utn.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {

    private final RestTemplate restTemplate;
    private final String baseUrl = "https://apis.datos.gob.ar/georef/api/v2.0/ubicacion";

    public GeocodingService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * Devuelve el nombre de la provincia argentina correspondiente a las coordenadas.
     */
    public String obtenerProvincia(double latitud, double longitud) {
        String url = String.format("%s?lat=%s&lon=%s", baseUrl, latitud, longitud);

        try {
            ResponseEntity<GeorefResponse> response = restTemplate.getForEntity(url, GeorefResponse.class);
            if (response.getBody() != null && response.getBody().ubicacion != null &&
                response.getBody().ubicacion.provincia != null) {
                return response.getBody().ubicacion.provincia.nombre;
            } else {
                return "Desconocida";
            }
        } catch (RestClientException e) {
            System.err.println("Error consultando Georef: " + e.getMessage());
            return "Desconocida";
        }
    }

    // Clases internas para mapear el JSON
    public static class GeorefResponse {
        public Ubicacion ubicacion;
    }

    public static class Ubicacion {
        public Provincia provincia;
    }

    public static class Provincia {
        public String id;
        public String nombre;
    }
}
