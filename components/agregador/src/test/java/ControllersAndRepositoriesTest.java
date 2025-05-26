import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import utn.controllers.ColeccionController;
import utn.controllers.ColeccionSeeder;
import utn.controllers.HechosController;
import utn.controllers.SolicitudController;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.domain.SolicitudEliminacion;
import utn.models.dtos.ColeccionDTO;
import utn.models.dtos.ColeccionMapper;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.helpers.Categoria;
import utn.models.helpers.EstadoSolicitud;
import utn.models.helpers.Ubicacion;
import utn.repositories.ColeccionRepository;
import utn.repositories.SolicitudRepository;
import utn.services.ColeccionService;
import utn.services.SolicitudService;
import utn.services.fuentes.IFuenteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllersAndRepositoriesTest {

    // --- ColeccionController ---
    @Test
    void testColeccionController_obtenerColecciones() {
        ColeccionService mockService = mock(ColeccionService.class);
        ColeccionSeeder mockSeeder = mock(ColeccionSeeder.class);

        Coleccion coleccion = new Coleccion("1", "Titulo", "Desc");
        when(mockService.obtenerColecciones()).thenReturn(List.of(coleccion));

        ColeccionController controller = new ColeccionController(mockService, mockSeeder);
        List<ColeccionDTO> dtos = controller.obtenerColecciones();

        assertEquals(1, dtos.size());
        assertEquals("Titulo", dtos.get(0).getTitulo());
    }

    @Test
    void testColeccionController_obtenerHechosPorColeccion() {
        ColeccionService mockService = mock(ColeccionService.class);
        ColeccionSeeder mockSeeder = mock(ColeccionSeeder.class);

        Hecho hecho = new Hecho(
                "Titulo", "Desc", new Categoria("Cat"),
                new Ubicacion(1.0, 2.0),
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 2),
                null
        );
        when(mockService.obtenerHechosPorColeccion("1")).thenReturn(List.of(hecho));

        ColeccionController controller = new ColeccionController(mockService, mockSeeder);
        List<HechoDTO> hechos = controller.obtenerHechosPorColeccion("1");

        assertEquals(1, hechos.size());
        assertEquals("Titulo", hechos.get(0).getTitulo());
    }

    @Test
    void testColeccionController_inicializarColecciones() {
        ColeccionService mockService = mock(ColeccionService.class);
        ColeccionSeeder mockSeeder = mock(ColeccionSeeder.class);

        ColeccionController controller = new ColeccionController(mockService, mockSeeder);
        ResponseEntity<Void> response = controller.inicializarColecciones();

        assertEquals(200, response.getStatusCodeValue());
        verify(mockSeeder, times(1)).seed();
    }

    // --- HechosController ---
    @Test
    void testHechosController_obtenerTodosLosHechos() {
        ColeccionService mockService = mock(ColeccionService.class);

        Hecho hecho = new Hecho(
                "Titulo", "Desc", new Categoria("Cat"),
                new Ubicacion(1.0, 2.0),
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 2),
                null
        );
        Coleccion coleccion = new Coleccion("1", "Col", "Desc");
        coleccion.setHechos(List.of(hecho));
        when(mockService.obtenerColecciones()).thenReturn(List.of(coleccion));

        HechosController controller = new HechosController(mockService);
        List<HechoDTO> hechos = controller.obtenerTodosLosHechos("Cat", LocalDate.of(2024,6,1), LocalDate.of(2024,6,2), 1.0, 2.0, "Titulo");

        assertEquals(1, hechos.size());
        assertEquals("Titulo", hechos.get(0).getTitulo());
    }

    // --- SolicitudController ---
    @Test
    void testSolicitudController_crearSolicitud() {
        SolicitudService mockService = mock(SolicitudService.class);

        Hecho hecho = new Hecho(
                "Titulo", "Desc", new Categoria("Cat"),
                new Ubicacion(1.0, 2.0),
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 2),
                null
        );
        String motivo = "Motivo de eliminación suficientemente largo";
        SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, motivo);
        SolicitudEliminacion nuevaSolicitud = new SolicitudEliminacion(hecho, motivo);

        when(mockService.crearSolicitud(solicitud)).thenReturn(nuevaSolicitud);

        SolicitudController controller = new SolicitudController(mockService);
        ResponseEntity<SolicitudEliminacion> response = controller.crearSolicitud(solicitud);

        assertEquals(nuevaSolicitud, response.getBody());
        verify(mockService, times(1)).crearSolicitud(solicitud);
    }

    // --- ColeccionRepository ---
    @Test
    void testColeccionRepository_save_findAll_findById_delete_clear() {
        ColeccionRepository repo = new ColeccionRepository();
        Coleccion coleccion = new Coleccion(null, "Titulo", "Desc");

        String id = repo.save(coleccion);
        assertNotNull(id);

        List<Coleccion> all = repo.findAll();
        assertEquals(1, all.size());

        Optional<Coleccion> found = repo.findById(id);
        assertTrue(found.isPresent());
        assertEquals("Titulo", found.get().getTitulo());

        repo.delete(id);
        assertTrue(repo.findAll().isEmpty());

        repo.save(new Coleccion(null, "T2", "D2"));
        repo.clear();
        assertTrue(repo.findAll().isEmpty());
    }

    // --- SolicitudRepository ---
    @Test
    void testSolicitudRepository_save_findAll_findByFact_delete() {
        SolicitudRepository repo = new SolicitudRepository();

        Hecho hecho = new Hecho(
                "Titulo", "Desc", new Categoria("Cat"),
                new Ubicacion(1.0, 2.0),
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 2),
                null
        );
        String motivo = "Motivo de eliminación suficientemente largo";
        SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, motivo);

        repo.save(solicitud);
        List<SolicitudEliminacion> all = repo.findAll();
        assertEquals(1, all.size());

        Optional<SolicitudEliminacion> found = repo.findByFact(hecho);
        assertTrue(found.isPresent());

        repo.delete(solicitud);
        assertTrue(repo.findAll().isEmpty());
    }

    @Test
    void testHechoConstructoresYGetters() {
        Hecho hecho1 = new Hecho("Descripción simple");
        assertEquals("Descripción simple", hecho1.getDescripcion());

        Hecho hecho2 = new Hecho(
            "Titulo", "Desc", new Categoria("Cat"),
            new Ubicacion(1.0, 2.0),
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 6, 2),
            null
        );
        assertEquals("Titulo", hecho2.getTitulo());
        assertEquals("Desc", hecho2.getDescripcion());
        assertEquals("Cat", hecho2.getCategoria().getNombre());
        assertEquals(1.0, hecho2.getUbicacion().getLatitud());
        assertEquals(2.0, hecho2.getUbicacion().getLongitud());
        assertEquals(LocalDate.of(2024, 6, 1), hecho2.getFecha());
        assertEquals(LocalDate.of(2024, 6, 2), hecho2.getFechaDeCarga());
        assertFalse(hecho2.isEliminado());
    }

    @Test
    void testHechoEtiquetas() {
        Hecho hecho = new Hecho(
            "Titulo", "Desc", new Categoria("Cat"),
            new Ubicacion(1.0, 2.0),
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 6, 2),
            null,
            List.of("et1", "et2")
        );
        assertEquals(2, hecho.getEtiquetas().size());
        hecho.agregarEtiqueta("et3");
        assertTrue(hecho.getEtiquetas().contains("et3"));
        // No debe duplicar etiquetas
        hecho.agregarEtiqueta("et3");
        assertEquals(3, hecho.getEtiquetas().size());
    }

    @Test
    void testHechoSolicitarEliminacion() {
        Hecho hecho = new Hecho(
            "Titulo", "Desc", new Categoria("Cat"),
            new Ubicacion(1.0, 2.0),
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 6, 2),
            null
        );
        SolicitudEliminacion solicitud = hecho.solicitarEliminacion("Motivo suficientemente largo para testear");
        assertEquals(hecho, solicitud.getHecho());
        assertEquals("Motivo suficientemente largo para testear", solicitud.getMotivo());
        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
        assertNotNull(solicitud.getFechaCreacion());
    }

    @Test
    void testHechoMarcarComoEliminado() {
        Hecho hecho = new Hecho("Titulo", "Desc", new Categoria("Cat"),
            new Ubicacion(1.0, 2.0), LocalDate.now(), LocalDate.now(), null);
        assertFalse(hecho.isEliminado());
        hecho.marcarComoEliminado();
        assertTrue(hecho.isEliminado());
        assertFalse(hecho.puedeAgregarseacoleccion());
    }

    @Test
    void testColeccionConstructoresYGetters() {
        Coleccion coleccion = new Coleccion("1", "Titulo", "Desc");
        assertEquals("1", coleccion.getId());
        assertEquals("Titulo", coleccion.getTitulo());
        assertEquals("Desc", coleccion.getDescripcion());
        assertNotNull(coleccion.getHechos());
        assertTrue(coleccion.getHechos().isEmpty());
    }

    @Test
    void testColeccionAgregarHechoYFiltrado() {
        Coleccion coleccion = new Coleccion("1", "Titulo", "Desc");
        Hecho hecho = new Hecho("Titulo", "Desc", new Categoria("Cat"),
            new Ubicacion(1.0, 2.0), LocalDate.now(), LocalDate.now(), null);
        coleccion.agregarHecho(hecho);
        assertEquals(1, coleccion.getHechos().size());
        // Si el hecho está eliminado, no debe aparecer en getHechos
        hecho.marcarComoEliminado();
        assertTrue(coleccion.getHechos().isEmpty());
    }

    @Test
    void testColeccionActualizarHechos() {
        // Mock de IFuenteService usando lambda
        utn.services.fuentes.IFuenteService fuente = () -> List.of(
            new Hecho("T1", "D1", new Categoria("C1"), new Ubicacion(1.0, 2.0), LocalDate.now(), LocalDate.now(), null)
        );
        Coleccion coleccion = new Coleccion("1", "Titulo", "Desc");
        coleccion.setFuentes(List.of(fuente));
        coleccion.actualizarHechos();
        assertEquals(1, coleccion.getHechos().size());
    }

    @Test
    void testSolicitudEliminacionConstructorYEstados() {
        Hecho hecho = new Hecho("Titulo", "Desc", new Categoria("Cat"),
            new Ubicacion(1.0, 2.0), LocalDate.now(), LocalDate.now(), null);
        String motivo = "Motivo suficientemente largo para testear";
        SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, motivo);

        assertEquals(hecho, solicitud.getHecho());
        assertEquals(motivo, solicitud.getMotivo());
        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
        assertNotNull(solicitud.getFechaCreacion());

        solicitud.aceptar();
        assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado());
        assertTrue(hecho.isEliminado());

        // Nuevo hecho para rechazar
        Hecho hecho2 = new Hecho("Titulo2", "Desc2", new Categoria("Cat2"),
            new Ubicacion(2.0, 3.0), LocalDate.now(), LocalDate.now(), null);
        SolicitudEliminacion solicitud2 = new SolicitudEliminacion(hecho2, motivo);
        solicitud2.rechazar();
        assertEquals(EstadoSolicitud.RECHAZADA, solicitud2.getEstado());
    }

    @Test
    void testSolicitudEliminacionMotivoCorto() {
        Hecho hecho = new Hecho("Titulo", "Desc", new Categoria("Cat"),
            new Ubicacion(1.0, 2.0), LocalDate.now(), LocalDate.now(), null);
        assertThrows(IllegalArgumentException.class, () -> {
            new SolicitudEliminacion(hecho, "Muy corto");
        });
    }
}