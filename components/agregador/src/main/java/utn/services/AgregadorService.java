package utn.services;

import jakarta.transaction.Transactional;
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
import utn.models.helpers.HechoClaveUtils;
import utn.repositories.IColeccionRepository;
import utn.repositories.IHechoRepository;
import utn.services.rabbitMQ.RabbitPublisher;

import java.util.*;

@Service
public class AgregadorService {

    private final RestTemplate restTemplate;
    private final IHechoRepository hechoRepo;
    private final IColeccionRepository coleccionRepo;
    private final FuenteProvider fuenteProvider;
    private final NormalizadorHechos normalizadorHechos;

    // Publicador para RabbitMQ
    private final RabbitPublisher publisherService;

    public AgregadorService(RestTemplateBuilder builder,
                            IHechoRepository hechoRepo,
                            IColeccionRepository coleccionRepo,
                            FuenteProvider fuenteProvider,
                            RabbitPublisher publisherService,
                            NormalizadorHechos normalizadorHechos) {
        this.restTemplate = builder.build();
        this.hechoRepo = hechoRepo;
        this.coleccionRepo = coleccionRepo;
        this.fuenteProvider = fuenteProvider;
        this.publisherService = publisherService;
        this.normalizadorHechos = normalizadorHechos;
    }

    /**
     * Método principal: carga hechos desde las fuentes y los asigna a colecciones
     */
    public void cargarHechosYAsignar() {
        consolidarHechosDesdeFuentes();
        asignarHechosAColecciones();
    }

    /**
     * Consolida hechos que vienen de las fuentes externas y los guarda en la BD
     */
    private void consolidarHechosDesdeFuentes() {
        Map<FuenteNombre, List<HechoDTO>> hechosPorFuente = new HashMap<>();

        // Traer hechos de cada fuente
        for (FuenteNombre fuente : fuenteProvider.getTodasLasFuentes()) {
            String url = fuenteProvider.getUrl(fuente);
            List<HechoDTO> hechos = obtenerHechosDesdeUrl(url);
            hechosPorFuente.put(fuente, hechos);
        }

        List<Hecho> nuevosHechos = new ArrayList<>();

        // Iterar cada fuente y sus hechos
        for (Map.Entry<FuenteNombre, List<HechoDTO>> entry : hechosPorFuente.entrySet()) {
            FuenteNombre fuente = entry.getKey();

            for (HechoDTO dto : entry.getValue()) {
                Hecho hecho = HechoMapper.aDominio(dto);

                // Generar clave lógica determinista
                hecho.setClaveLogica(HechoClaveUtils.generarClaveLogica(hecho));

                // Buscar si ya existe por clave lógica
                Optional<Hecho> existente = hechoRepo.findByClaveLogica(hecho.getClaveLogica());

                if (existente.isPresent()) {
                    // Si existe, solo agrego la fuente y hago update
                    Hecho existenteHecho = existente.get();
                    existenteHecho.agregarFuente(fuente);
                    hechoRepo.saveAndFlush(existenteHecho); // forzar flush inmediato
                } else {
                    // Si no existe, normalizo y guardo
                    hecho.agregarFuente(fuente);
                    hecho = normalizadorHechos.normalizar(hecho);
                    nuevosHechos.add(hecho);

                    // Guardar inmediatamente para que futuras búsquedas lo vean
                    hechoRepo.saveAndFlush(hecho);
                }
            }
        }

        System.out.println("Hechos ingresando: " + nuevosHechos.size());

        // Publicar hechos nuevos a Rabbit
        publisherService.publicarHechos(
                nuevosHechos.stream().map(HechoMapper::aDTO).toList()
        );
    }






    /**
     * Asigna hechos ya existentes en BD a las colecciones correspondientes
     */
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

    /**
     * Consulta una fuente externa y devuelve hechos DTO
     */
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