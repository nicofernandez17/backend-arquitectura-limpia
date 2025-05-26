import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utn.controllers.HechosController;
import utn.models.dtos.HechoDTO;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.helpers.Categoria;
import utn.models.helpers.Ubicacion;
import utn.services.ColeccionService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HechosControllerTest {

  private ColeccionService coleccionService;
  private HechosController controller;

  @BeforeEach
  void setUp() {
    coleccionService = mock(ColeccionService.class);
    controller = new HechosController(coleccionService);
  }

  @Test
  void testFiltrarPorCategoria() {
    Hecho hecho = mock(Hecho.class);
    when(hecho.getCategoria()).thenReturn(new Categoria("Policial"));
    Coleccion coleccion = mock(Coleccion.class);
    when(coleccion.getHechos()).thenReturn(List.of(hecho));
    when(coleccionService.obtenerColecciones()).thenReturn(List.of(coleccion));

    List<HechoDTO> resultado = controller.obtenerTodosLosHechos("Policial", null, null, null, null, null);

    assertEquals(1, resultado.size());
  }

  @Test
  void testFiltrarPorFecha() {
    Hecho hecho = mock(Hecho.class);
    when(hecho.getFecha()).thenReturn(LocalDate.of(2020, 1, 1));
    Coleccion coleccion = mock(Coleccion.class);
    when(coleccion.getHechos()).thenReturn(List.of(hecho));
    when(coleccionService.obtenerColecciones()).thenReturn(List.of(coleccion));

    List<HechoDTO> resultado = controller.obtenerTodosLosHechos(
        null,
        LocalDate.of(2019, 1, 1),
        LocalDate.of(2021, 1, 1),
        null, null, null);

    assertEquals(1, resultado.size());
  }

  @Test
  void testFiltrarPorUbicacion() {
    Hecho hecho = mock(Hecho.class);
    Ubicacion ubicacion = new Ubicacion(10.0, 20.0);
    when(hecho.getUbicacion()).thenReturn(ubicacion);
    when(hecho.getCategoria()).thenReturn(new Categoria("Cualquiera"));
    when(hecho.getFecha()).thenReturn(LocalDate.now());
    when(hecho.getTitulo()).thenReturn("Titulo");
    Coleccion coleccion = mock(Coleccion.class);
    when(coleccion.getHechos()).thenReturn(List.of(hecho));
    when(coleccionService.obtenerColecciones()).thenReturn(List.of(coleccion));

    List<HechoDTO> resultado = controller.obtenerTodosLosHechos(
        null, null, null,
        10.0, 20.0, null);

    assertEquals(1, resultado.size());
  }

  // testFiltrarPorTitulo
  @Test
  void testFiltrarPorTitulo() {
    Hecho hecho = mock(Hecho.class);
    when(hecho.getTitulo()).thenReturn("Colapinto en la pista");
    when(hecho.getCategoria()).thenReturn(new Categoria("Carrera"));
    when(hecho.getFecha()).thenReturn(LocalDate.now());
    when(hecho.getUbicacion()).thenReturn(new Ubicacion(0.0, 0.0));
    Coleccion coleccion = mock(Coleccion.class);
    when(coleccion.getHechos()).thenReturn(List.of(hecho));
    when(coleccionService.obtenerColecciones()).thenReturn(List.of(coleccion));

    List<HechoDTO> resultado = controller.obtenerTodosLosHechos(
        null, null, null,
        null, null, "Colapinto en la pista");

    assertEquals(1, resultado.size());
  }

  @Test
  void testSinFiltrosDevuelveTodos() {
    Hecho hecho1 = mock(Hecho.class);
    Hecho hecho2 = mock(Hecho.class);
    Coleccion coleccion = mock(Coleccion.class);
    when(coleccion.getHechos()).thenReturn(List.of(hecho1, hecho2));
    when(coleccionService.obtenerColecciones()).thenReturn(List.of(coleccion));

    List<HechoDTO> resultado = controller.obtenerTodosLosHechos(
        null, null, null, null, null, null);

    assertEquals(2, resultado.size());
  }
}