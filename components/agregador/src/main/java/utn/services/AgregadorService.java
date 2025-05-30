package utn.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import utn.configs.FuenteConfig;
import utn.models.domain.Hecho;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.repositories.HechoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AgregadorService {

    private final RestTemplate restTemplate;
    private final FuenteConfig fuenteConfig;
    private final HechoRepository hechoRepository;

    public AgregadorService(
            RestTemplateBuilder builder,
            FuenteConfig fuenteConfig,
            HechoRepository hechoRepository
    ) {
        this.restTemplate = builder.build();
        this.fuenteConfig = fuenteConfig;
        this.hechoRepository = hechoRepository;
    }

    public List<Hecho> obtenerYGuardarHechos() {
        List<Hecho> hechosTotales = new ArrayList<>();

        for (String url : fuenteConfig.getUrls()) {
            List<HechoDTO> hechosDTO = obtenerHechosDesdeUrl(url);

            // Convertir a dominio
            List<Hecho> hechos = hechosDTO.stream()
                    .map(HechoMapper::aDominio)
                    .toList();

            // Guardar en repositorio
            hechoRepository.saveAll(hechos);

            // Acumular para retornar
            hechosTotales.addAll(hechos);
        }

        return hechosTotales;
    }

    private List<HechoDTO> obtenerHechosDesdeUrl(String url) {
        try {
            ResponseEntity<List<HechoDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (RestClientException ex) {
            System.err.println("Error al consumir hechos desde: " + url);
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }
}