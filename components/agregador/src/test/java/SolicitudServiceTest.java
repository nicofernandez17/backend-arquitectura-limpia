import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.domain.SolicitudEliminacion;
import utn.models.helpers.EstadoSolicitud;
import utn.repositories.SolicitudRepository;
import utn.services.ColeccionService;
import utn.services.SolicitudService;
import utn.services.spamDetector.ISpamDetector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SolicitudServiceTest {

    private SolicitudRepository solicitudRepository;
    private ISpamDetector spamDetector;
    private SolicitudService solicitudService;

    @BeforeEach
    void setUp() {
        solicitudRepository = mock(SolicitudRepository.class);
        spamDetector = mock(ISpamDetector.class);
        solicitudService = new SolicitudService(solicitudRepository, spamDetector);
    }

    @Test
    void testCrearSolicitud_NoEsSpam() {
        Hecho hecho = mock(Hecho.class);
        String motivoValido = "Este es un motivo suficientemente largo";
        SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, motivoValido);

        when(spamDetector.esSpam(motivoValido)).thenReturn(false);

        SolicitudEliminacion resultado = solicitudService.crearSolicitud(solicitud);

        assertEquals(EstadoSolicitud.PENDIENTE, resultado.getEstado());
        verify(solicitudRepository).save(solicitud);
    }

    @Test
    void testCrearSolicitud_EsSpam() {
        Hecho hecho = mock(Hecho.class);
        String motivoValido = "Este motivo también es suficientemente largo";
        SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, motivoValido);

        when(spamDetector.esSpam(motivoValido)).thenReturn(true);

        SolicitudEliminacion resultado = solicitudService.crearSolicitud(solicitud);

        assertEquals(EstadoSolicitud.RECHAZADA, resultado.getEstado());
        verify(solicitudRepository).save(solicitud);
    }

    @Test
    void testConstructor_MotivoCorto_LanzaExcepcion() {
        Hecho hecho = mock(Hecho.class);
        String motivoCorto = "Muy corto";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SolicitudEliminacion(hecho, motivoCorto);
        });
        assertTrue(exception.getMessage().contains("al menos 20 caracteres"));
    }

    @Test
    void testAceptarCambiaEstadoYMarcaHechoComoEliminado() {
        Hecho hecho = mock(Hecho.class);
        SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, "Motivo suficientemente largo para test");

        solicitud.aceptar();

        assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado());
        verify(hecho).marcarComoEliminado();
    }

    @Test
    void testRechazarCambiaEstado() {
        Hecho hecho = mock(Hecho.class);
        SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, "Motivo suficientemente largo para test");

        solicitud.rechazar();

        assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado());
    }

    @Test
    void testGetters() {
        Hecho hecho = mock(Hecho.class);
        String motivo = "Motivo suficientemente largo para test";
        SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, motivo);

        assertEquals(hecho, solicitud.getHecho());
        assertEquals(motivo, solicitud.getMotivo());
        assertNotNull(solicitud.getFechaCreacion());
    }

    @Test
    void testActualizarHechosDeTodasLasColecciones_Vacio() {
        ColeccionRepository repo = mock(ColeccionRepository.class);
        when(repo.findAll()).thenReturn(Collections.emptyList());
        ColeccionService service = new ColeccionService(repo);

        assertDoesNotThrow(service::actualizarHechosDeTodasLasColecciones);
    }

    @Test
    void testActualizarHechosDeTodasLasColecciones_ConColecciones() {
        Coleccion coleccion = mock(Coleccion.class);
        ColeccionRepository repo = mock(ColeccionRepository.class);
        when(repo.findAll()).thenReturn(List.of(coleccion));
        ColeccionService service = new ColeccionService(repo);

        service.actualizarHechosDeTodasLasColecciones();
        verify(coleccion).actualizarHechos();
    }

    @Test
    void testObtenerColecciones_ListaVacia() {
        ColeccionRepository repo = mock(ColeccionRepository.class);
        when(repo.findAll()).thenReturn(Collections.emptyList());
        ColeccionService service = new ColeccionService(repo);

        List<Coleccion> resultado = service.obtenerColecciones();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void testObtenerHechosPorColeccion_Existe() {
        Coleccion coleccion = mock(Coleccion.class);
        List<Hecho> hechos = List.of(mock(Hecho.class));
        when(coleccion.getHechos()).thenReturn(hechos);

        ColeccionRepository repo = mock(ColeccionRepository.class);
        when(repo.findById("id")).thenReturn(Optional.of(coleccion));
        ColeccionService service = new ColeccionService(repo);

        List<Hecho> resultado = service.obtenerHechosPorColeccion("id");
        assertEquals(hechos, resultado);
    }

    @Test
    void testObtenerHechosPorColeccion_NoExiste() {
        ColeccionRepository repo = mock(ColeccionRepository.class);
        when(repo.findById("id")).thenReturn(Optional.empty());
        ColeccionService service = new ColeccionService(repo);

        assertThrows(Exception.class, () -> service.obtenerHechosPorColeccion("id"));
    }
}