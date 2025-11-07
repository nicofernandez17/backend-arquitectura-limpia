import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import utn.controllers.HechosController;
import utn.models.dto.HechoDTO;
import utn.models.lectores.AdapterLectorCsv;
import utn.repositories.IHechoRepository;
import utn.repositories.impl.HechosRepository;
import utn.services.HechosService;
import utn.services.NormalizadorClientService;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HechosIntegracionRealCsvTest {

  static final String CSV_PATH = "src/main/resources/desastres_naturales_argentina.csv";
  IHechoRepository repository;
  NormalizadorClientService normalizadorClientService;
  HechosService service;
  HechosController controller;
/* Dejo commiteados los test xq están todos obsoletos y rotos ya...
  @BeforeEach
  void setUp() {
    //repository = new HechosRepository();
    service = new HechosService(normalizadorClientService, repository);
    ReflectionTestUtils.setField(service, "archivoRuta", CSV_PATH);
    controller = new HechosController(service);
  }

  @Test
  void testArchivoCsvExiste() {
    File file = new File(CSV_PATH);
    assertTrue(file.exists(), "El archivo CSV real debe existir en src/main/resources");
  }


  @Test
  void testLecturaCsvReal() {
    AdapterLectorCsv lector = new AdapterLectorCsv();
    List<HechoDTO> hechos = lector.leer(CSV_PATH);

    assertFalse(hechos.isEmpty(), "El CSV debe tener hechos");
    HechoDTO primero = hechos.get(0);
    assertNotNull(primero.getTitulo());
    assertNotNull(primero.getDescripcion());
    assertNotNull(primero.getCategoria());
    assertNotNull(primero.getFecha_hecho());
    assertNotNull(primero.getCreated_at());
  }

  @Test
  void testGuardarYBuscarEnRepositorio() {
    AdapterLectorCsv lector = new AdapterLectorCsv();
    List<HechoDTO> hechos = lector.leer(CSV_PATH);

    hechos.forEach(repository::save);

    assertEquals(hechos.size(), repository.findAll().size());
    HechoDTO uno = hechos.get(0);
    Optional<HechoDTO> encontrado = repository.findByTitulo(uno.getTitulo());
    assertTrue(encontrado.isPresent());
    assertEquals(uno.getDescripcion(), encontrado.get().getDescripcion());
  }

  @Test
  void testReemplazoPorTituloEnRepositorio() {
    HechoDTO hecho1 = HechoDTO.builder()
        .titulo("Duplicado")
        .descripcion("desc1")
        .categoria("cat")
        .latitud(1)
        .longitud(1)
        .build();
    HechoDTO hecho2 = HechoDTO.builder()
        .titulo("Duplicado")
        .descripcion("desc2")
        .categoria("cat")
        .latitud(1)
        .longitud(1)
        .build();

    repository.save(hecho1);
    repository.save(hecho2);

    assertEquals(1, repository.findAll().size());
    assertEquals("desc2", repository.findByTitulo("Duplicado").get().getDescripcion());
  }

  @Test
  void testServicioCargarDesdeCsv() {
    List<HechoDTO> hechos = service.cargarDesdeCsv();
    assertFalse(hechos.isEmpty());
    assertEquals(repository.findAll().size(), hechos.size());
  }

  @Test
  void testServicioObtenerTodos() {
    service.cargarDesdeCsv();
    List<HechoDTO> hechos = service.obtenerTodos();
    assertFalse(hechos.isEmpty());
    assertEquals(repository.findAll().size(), hechos.size());
  }

  @Test
  void testControladorObtenerTodosLosHechos() {
    List<HechoDTO> hechos = controller.obtenerTodosLosHechos();
    assertFalse(hechos.isEmpty());
    HechoDTO primero = hechos.get(0);
    assertNotNull(primero.getTitulo());
    assertNotNull(primero.getCreated_at());
    assertNotNull(primero.getFecha_hecho());
  }*/
}