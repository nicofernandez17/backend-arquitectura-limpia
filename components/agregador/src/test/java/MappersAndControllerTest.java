import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import utn.controllers.SolicitudController;
import utn.models.domain.Hecho;
import utn.models.domain.SolicitudEliminacion;
import utn.models.dtos.ColeccionDTO;
import utn.models.dtos.ColeccionMapper;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.helpers.Categoria;
import utn.models.helpers.Ubicacion;
import utn.services.SolicitudService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MappersAndControllerTest {

    // Test para HechoMapper.aDTO
    @Test
    void testHechoMapper_aDTO() {
        Hecho hecho = new Hecho(
                "Titulo",
                "Descripcion",
                new Categoria("Cat"),
                new Ubicacion(1.0, 2.0),
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 2),
                null
        );
        HechoDTO dto = HechoMapper.aDTO(hecho);
        assertEquals("Titulo", dto.getTitulo());
        assertEquals("Descripcion", dto.getDescripcion());
        assertEquals(1.0, dto.getLatitud());
        assertEquals(2.0, dto.getLongitud());
        assertNotNull(dto.getFecha_hecho());
        assertNotNull(dto.getCreated_at());
    }

    // Test para HechoMapper.aDominio
    @Test
    void testHechoMapper_aDominio() {
        HechoDTO dto = HechoDTO.builder()
                .titulo("Titulo")
                .descripcion("Descripcion")
                .categoria("Cat")
                .latitud(1.0)
                .longitud(2.0)
                .fecha_hecho(LocalDateTime.of(2024, 6, 1, 0, 0))
                .created_at(LocalDateTime.of(2024, 6, 2, 0, 0))
                .build();
        Hecho hecho = HechoMapper.aDominio(dto);
        assertEquals("Titulo", hecho.getTitulo());
        assertEquals("Descripcion", hecho.getDescripcion());
        assertEquals("Cat", hecho.getCategoria().getNombre());
        assertEquals(1.0, hecho.getUbicacion().getLatitud());
        assertEquals(2.0, hecho.getUbicacion().getLongitud());
        assertEquals(LocalDate.of(2024, 6, 1), hecho.getFecha());
        assertEquals(LocalDate.of(2024, 6, 2), hecho.getFechaDeCarga());
    }

    // Test para ColeccionMapper.toDTO
    @Test
    void testColeccionMapper_toDTO() {
        Hecho hecho = new Hecho(
                "Titulo",
                "Descripcion",
                new Categoria("Cat"),
                new Ubicacion(1.0, 2.0),
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 2),
                null
        );
        utn.models.domain.Coleccion coleccion = new utn.models.domain.Coleccion("1", "Coleccion1", "desc");
        coleccion.setHechos(List.of(hecho));
        ColeccionDTO dto = ColeccionMapper.toDTO(coleccion);
        assertEquals("1", dto.getId());
        assertEquals("Coleccion1", dto.getTitulo());
        assertEquals(1, dto.getHechos().size());
        assertEquals("Titulo", dto.getHechos().get(0).getTitulo());
    }

    // Test para SolicitudController.crearSolicitud
    @Test
    void testSolicitudController_crearSolicitud() {
        SolicitudService mockService = mock(SolicitudService.class);

        Hecho hecho = new Hecho(
                "Titulo",
                "Descripcion",
                new Categoria("Cat"),
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
}