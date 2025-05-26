import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import utn.models.dtos.ColeccionDTO;
import utn.repositories.ColeccionRepository;
import utn.repositories.HechoRepository;
import utn.services.AgregadorService;
import utn.services.ColeccionesService;
import utn.services.HechosService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServiciosYRepositoriosTest {

    ColeccionRepository coleccionRepository;
    HechoRepository hechoRepository;
    HechosService hechosService;
    ColeccionesService coleccionesService;

    @BeforeEach
    void setUp() {
        coleccionRepository = new ColeccionRepository();
        hechoRepository = new HechoRepository();
        hechosService = new HechosService();
        coleccionesService = new ColeccionesService();
    }

    @Test
    void testInstanciacionRepositoriosYServicios() {
        assertNotNull(coleccionRepository);
        assertNotNull(hechoRepository);
        assertNotNull(hechosService);
        assertNotNull(coleccionesService);
    }

    @Test
    void agregadorServiceObtenerColeccionesDevuelveLista() {
        WebClient.Builder builder = mock(WebClient.Builder.class, RETURNS_DEEP_STUBS);
        WebClient webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);
        ColeccionRepository repo = mock(ColeccionRepository.class);

        when(builder.baseUrl(anyString())).thenReturn(builder);
        when(builder.build()).thenReturn(webClient);

        List<ColeccionDTO> colecciones = List.of(
            ColeccionDTO.builder().id("1").titulo("t1").hechos(List.of()).build()
        );

        // Encadena los when para el mock profundo
        when(webClient.get()
            .uri(anyString())
            .retrieve()
            .bodyToFlux(ColeccionDTO.class))
            .thenReturn(Flux.fromIterable(colecciones));

        AgregadorService service = new AgregadorService(builder, repo);
        List<ColeccionDTO> resultado = service.obtenerColecciones();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("1", resultado.get(0).getId());
    }

}