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

        // Traigo hechos de cada fuente
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

                // Generar hash único y mostrarlo
                String hash = HechoClaveUtils.generarClaveHash(hecho);
                hecho.setClaveHash(hash);

                String claveLogica = HechoClaveUtils.generarClaveLogica(hecho);
                hecho.setClaveLogica(claveLogica);

                System.out.println("Procesando hecho -> Título: " + hecho.getTitulo() +
                        ", Fecha: " + hecho.getFecha() +
                        ", ClaveHash: " + hash +
                        ", ClaveLogica: " + claveLogica);

                // Verificar si ya existe por claveHash
                Optional<Hecho> existente = hechoRepo.findByClaveHash(hash);

                if (existente.isPresent()) {
                    System.out.println("Hecho ya existe, actualizando fuentes...");
                    Hecho existenteHecho = existente.get();
                    existenteHecho.agregarFuente(fuente);
                    //TODO aca comente para probar, segun gpt se persiste automaticamente al traerlo con findByClaveHash
                    hechoRepo.save(existenteHecho); // update
                } else {
                    // Normalizo y guardo
                    hecho.agregarFuente(fuente);
                    hecho = normalizadorHechos.normalizar(hecho);
                    nuevosHechos.add(hecho);
                    System.out.println("Insertando nuevo hecho con hash: " + hash);
                    hechoRepo.save(hecho); // insert
                }
            }
        }

        System.out.println("Total de nuevos hechos insertados: " + nuevosHechos.size());

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


