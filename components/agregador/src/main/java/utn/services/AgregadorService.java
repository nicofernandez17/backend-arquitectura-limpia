package utn.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.helpers.FuenteNombre;
import utn.models.helpers.NormalizadorHechos;
import utn.repositories.ColeccionRepository;
import utn.repositories.HechoRepository;
import utn.services.rabbitMQ.RabbitPublisher;

import java.util.*;

@Service
public class AgregadorService {

    private final RestTemplate restTemplate;
    private final HechoRepository hechoRepo;
    private final ColeccionRepository coleccionRepo;
    private final FuenteProvider fuenteProvider;
    private final NormalizadorHechos normalizadorHechos;

    /* Esto hay que usarlo cuando entre nuevos hechos para que los publique a
    la cola a la que suscriben las fuentes proxys conectadas a esta instancia.
    Se usa con publisherService.enviarHechos(List<HechoDTO>)*/
    private final RabbitPublisher publisherService;

    public AgregadorService(RestTemplateBuilder builder,
                            HechoRepository hechoRepo,
                            ColeccionRepository coleccionRepo,
                            FuenteProvider fuenteProvider,
                            RabbitPublisher publisherService) {
        this.restTemplate = builder.build();
        this.hechoRepo = hechoRepo;
        this.coleccionRepo = coleccionRepo;
        this.fuenteProvider = fuenteProvider;
        this.publisherService = publisherService;
        this.normalizadorHechos = new NormalizadorHechos();
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
        System.out.println("Hechos ingresando: " + nuevosHechos.size());
        // Normalizar nuevosHechos antes del saveAll
        List<Hecho> nuevosHechosNormalizados = nuevosHechos.stream().map(normalizadorHechos::normalizar).toList();

        hechoRepo.saveAll(nuevosHechosNormalizados);

        // le paso al publisher los nuevos hechos en formato DTO
        publisherService.publicarHechos(nuevosHechos.stream().map(HechoMapper::aDTO).toList());
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