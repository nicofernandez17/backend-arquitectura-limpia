package ar.edu.utn.frba.dds;

import deprecated.criterios.CriterioFiltroTitulo;
import deprecated.criterios.CriterioPorCategoria;
import deprecated.criterios.CriterioPorFecha;
import deprecated.Coleccion;
import utn.models.domain.Hecho;
import deprecated.fuentes.FuenteDatos;
import utn.models.helpers.Categoria;
import deprecated.ColeccionBuilder;
import utn.models.helpers.Origen;
import utn.models.helpers.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class Escenario1Test {

  Hecho hecho1;
  Hecho hecho2;
  Hecho hecho3;
  Hecho hecho4;
  Hecho hecho5;
  Coleccion coleccion;
  FuenteDatos fuente;

  @BeforeEach
  public void init(){

    hecho1 = new Hecho(
            "Caída de aeronave impacta en Olavarría",
            "Grave caída de aeronave ocurrió en las inmediaciones de Olavarría, Buenos Aires.",
            new Categoria("CAIDA_DE_AERONAVE"),
            new Ubicacion(-36.868375, -60.343297),
            LocalDate.of(2001, 11, 29),
            LocalDate.now(),
            Origen.CARGA_MANUAL,
            List.of("Olavarria")
    );

    hecho2 = new Hecho(
            "Serio incidente: Accidente con maquinaria industrial en Chos Malal, Neuquén",
            "Un grave accidente con maquinaria industrial se registró en Chos Malal, Neuquén.",
            new Categoria("ACCIDENTE_CON_MAQUINARIA"),
            new Ubicacion(-37.345571, -70.241485),
            LocalDate.of(2001, 8, 16),
            LocalDate.now(),
            Origen.CARGA_MANUAL
    );

    hecho3 = new Hecho(
            "Caída de aeronave impacta en Venado Tuerto, Santa Fe",
            "Grave caída de aeronave ocurrió en las inmediaciones de Venado Tuerto, Santa Fe.",
            new Categoria("CAIDA_DE_AERONAVE"),
            new Ubicacion(-33.768051, -61.921032),
            LocalDate.of(2008, 8, 8),
            LocalDate.now(),
            Origen.CARGA_MANUAL
    );

    hecho4 = new Hecho(
            "Accidente en paso a nivel deja múltiples daños en Pehuajó, Buenos Aires",
            "Grave accidente en paso a nivel ocurrió en las inmediaciones de Pehuajó, Buenos Aires.",
            new Categoria("ACCIDENTE_EN_PASO_A_NIVEL"),
            new Ubicacion(-35.855811, -61.940589),
            LocalDate.of(2020, 1, 27),
            LocalDate.now(),
            Origen.CARGA_MANUAL
    );

    hecho5 = new Hecho(
            "Devastador Derrumbe en obra en construcción afecta a Presidencia Roque Sáenz Peña",
            "Un grave derrumbe en obra en construcción se registró en Presidencia Roque Sáenz Peña, Chaco.",
            new Categoria("Derrumbe en obra en construcción"),
            new Ubicacion(-26.780008, -60.458782),
            LocalDate.of(2016, 6, 4),
            LocalDate.now(),
            Origen.CARGA_MANUAL
    );

    // Mockear la fuente
    fuente = Mockito.mock(FuenteDatos.class);

    when(fuente.obtenerHechos()).thenReturn(List.of(hecho1, hecho2, hecho3, hecho4, hecho5));

    CriterioPorFecha criterioFecha = new CriterioPorFecha(LocalDate.of(2000, 1, 1), LocalDate.of(2025, 12, 31));

    // Construcción de la colección
    ColeccionBuilder builder = new ColeccionBuilder();
    coleccion = builder
            .iniciarCon("Colección prueba", "Esto es una prueba", fuente, null)
            .buildHechos()
            .build();

  }

  @Test
  public void testCreacionDeColeccionConCargaManual() {

    // Validación
    List<Hecho> hechos = coleccion.getHechos();
    assertEquals(5, hechos.size());

  }

  @Test
  public void testCriteriosDePertenencia() {

    // Agregar criterio de pertenencia por rango de fechas
    CriterioPorFecha criterioFecha = new CriterioPorFecha(LocalDate.of(2000, 1, 1), LocalDate.of(2010, 1, 1));
    coleccion.agregarCriterio(criterioFecha);
    coleccion.cargarColeccion();

    // Validar que solo queden los primeros tres hechos
    List<Hecho> hechosFiltrados = coleccion.getHechos();
    assertEquals(3, hechosFiltrados.size());
    assertEquals("Caída de aeronave impacta en Olavarría", hechosFiltrados.get(0).getTitulo());
    assertEquals("Serio incidente: Accidente con maquinaria industrial en Chos Malal, Neuquén", hechosFiltrados.get(1).getTitulo());
    assertEquals("Caída de aeronave impacta en Venado Tuerto, Santa Fe", hechosFiltrados.get(2).getTitulo());

    // Agregar criterio de pertenencia por categoría
    CriterioPorCategoria criterioCategoria = new CriterioPorCategoria(new Categoria("CAIDA_DE_AERONAVE"));
    coleccion.agregarCriterio(criterioCategoria);
    coleccion.cargarColeccion();

    // Validar que el segundo hecho ya no esté presente
    hechosFiltrados = coleccion.getHechos();
    assertEquals(2, hechosFiltrados.size());
    assertEquals("Caída de aeronave impacta en Olavarría", hechosFiltrados.get(0).getTitulo());
    assertEquals("Caída de aeronave impacta en Venado Tuerto, Santa Fe", hechosFiltrados.get(1).getTitulo());
  }

  @Test
  public void testFiltroVisualizador() {

    // Crear criterios
    CriterioFiltroTitulo criterioTitulo = new CriterioFiltroTitulo("un título");
    CriterioPorCategoria criterioCategoria = new CriterioPorCategoria(new Categoria("Caída de Aeronave"));

    // Aplicar criterios
    coleccion.agregarCriterio(criterioTitulo);
    coleccion.agregarCriterio(criterioCategoria);
    coleccion.cargarColeccion();

    // Validar que no hay hechos que cumplan con el filtro
    assertEquals(0, coleccion.getHechos().size());
  }

  @Test
  public void testEtiquetasEnHecho() {

    // Agregar etiquetas
    hecho1.agregarEtiqueta("Grave");

    // Verificar que el hecho retenga ambas etiquetas
    assertEquals(2, hecho1.getEtiquetas().size());
    assertTrue(hecho1.getEtiquetas().contains("Olavarria"));
    assertTrue(hecho1.getEtiquetas().contains("Grave"));
  }
}