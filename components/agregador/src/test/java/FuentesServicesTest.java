import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.reactive.function.client.WebClient;
import utn.services.fuentes.FuenteDinamicaService;
import utn.services.fuentes.FuenteEstaticaService;
import utn.services.fuentes.FuenteProxyService;

import static org.junit.jupiter.api.Assertions.*;

class FuentesServicesTest {

    @Test
    void testFuenteDinamicaServiceObtenerHechosNoLanzaExcepcion() {
        WebClient.Builder builder = Mockito.mock(WebClient.Builder.class, Mockito.RETURNS_DEEP_STUBS);
        FuenteDinamicaService service = new FuenteDinamicaService(builder);
        assertDoesNotThrow(service::obtenerHechos);
    }

    @Test
    void testFuenteEstaticaServiceObtenerHechosNoLanzaExcepcion() {
        WebClient.Builder builder = Mockito.mock(WebClient.Builder.class, Mockito.RETURNS_DEEP_STUBS);
        FuenteEstaticaService service = new FuenteEstaticaService(builder);
        assertDoesNotThrow(service::obtenerHechos);
    }

    @Test
    void testFuenteProxyServiceObtenerHechosNoLanzaExcepcion() {
        WebClient.Builder builder = Mockito.mock(WebClient.Builder.class, Mockito.RETURNS_DEEP_STUBS);
        FuenteProxyService service = new FuenteProxyService(builder);
        assertDoesNotThrow(service::obtenerHechos);
    }
}