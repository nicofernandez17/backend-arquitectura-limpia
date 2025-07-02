package utn.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import utn.configs.FuenteConfig;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.dtos.ColeccionMapper;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.helpers.FuenteNombre;
import utn.repositories.ColeccionRepository;
import utn.repositories.HechoRepository;

import java.util.*;

@Service
public class AgregadorService {

    private final RestTemplate restTemplate;
    private final HechoRepository hechoRepo;
    private final ColeccionRepository coleccionRepo;
    private final FuenteProvider fuenteProvider;

    /* Esto hay que usarlo cuando entre nuevos hechos para que los publique a
    la cola a la que suscriben las fuentes proxys conectadas a esta instancia.
    Se usa con publisherService.enviarHechos(List<HechoDTO>)*/
    private final PublisherService publisherService;

    public AgregadorService(RestTemplateBuilder builder,
                            HechoRepository hechoRepo,
                            ColeccionRepository coleccionRepo,
                            FuenteProvider fuenteProvider,
                            PublisherService publisherService) {
        this.restTemplate = builder.build();
        this.hechoRepo = hechoRepo;
        this.coleccionRepo = coleccionRepo;
        this.fuenteProvider = fuenteProvider;
        this.publisherService = publisherService;
    }

    public void cargarHechosYAsignar() {
        consolidarHechosDesdeFuentes();
        asignarHechosAColecciones();
    }

    private void consolidarHechosDesdeFuentes() {
        Map<FuenteNombre, List<HechoDTO>> hechosPorFuente = new HashMap<>();

        for (FuenteNombre fuente : fuenteProvider.getTodasLasFuentes()) {
            String url = fuenteProvider.getUrl(fuente);
            List<HechoDTO> hechos = obtenerHechosDesdeUrl(url);
            hechosPorFuente.put(fuente, hechos);
        }

        List<Hecho> nuevosHechos = new ArrayList<>();

        for (Map.Entry<FuenteNombre, List<HechoDTO>> entry : hechosPorFuente.entrySet()) {
            FuenteNombre fuente = entry.getKey();

            for (HechoDTO dto : entry.getValue()) {
                Hecho hecho = HechoMapper.aDominio(dto);

                Optional<Hecho> existente = hechoRepo.findIgual(hecho);

                if (existente.isPresent()) {
                    Hecho existenteHecho = existente.get();
                    existenteHecho.agregarFuente(fuente);
                    hechoRepo.save(existenteHecho);
                } else {
                    hecho.setId(UUID.randomUUID().toString());
                    hecho.agregarFuente(fuente);
                    nuevosHechos.add(hecho);
                }
            }
        }

        hechoRepo.saveAll(nuevosHechos);

        // le paso al publisher los nuevos hechos en formato DTO
        publisherService.enviarHechos(nuevosHechos.stream().map(HechoMapper::aDTO).toList());
    }

    private void asignarHechosAColecciones() {
        List<Hecho> hechos = hechoRepo.findAll();
        List<Coleccion> colecciones = coleccionRepo.findAll();

        for (Coleccion coleccion : colecciones) {
            for (Hecho hecho : hechos) {
                for (FuenteNombre fuente : hecho.getFuentes()) {
                    if (coleccion.getFuentes().contains(fuente)) {
                        coleccion.agregarHecho(hecho);
                        break;
                    }
                }
            }
        }

        coleccionRepo.saveAll(colecciones);
    }

    private List<HechoDTO> obtenerHechosDesdeUrl(String url) {
        try {
            ResponseEntity<List<HechoDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            return response.getBody() != null ? response.getBody() : List.of();
        } catch (RestClientException e) {
            System.err.println("Error consultando " + url + ": " + e.getMessage());
            return List.of();
        }
    }


    
}