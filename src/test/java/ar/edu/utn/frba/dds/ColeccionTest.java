package ar.edu.utn.frba.dds;

import criterios.CriterioPorCategoria;
import criterios.CriterioPorFecha;
import domain.Coleccion;
import domain.Hecho;
import helpers.Categoria;
import helpers.ColeccionBuilder;
import helpers.Origen;
import helpers.Ubicacion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColeccionTest {

  @Test
  public void testCreacionDeColeccionConCargaManual() {
    // Datos de prueba
    Hecho hecho1 = new Hecho(
        "Caída de aeronave impacta en Olavarría",
        "Grave caída de aeronave ocurrió en las inmediaciones de Olavarría, Buenos Aires.",
        new Categoria("CAIDA_DE_AERONAVE"),
        new Ubicacion(-36.868375, -60.343297),
        LocalDate.of(2001, 11, 29),
        LocalDate.now(),
        Origen.CARGA_MANUAL
    );

    Hecho hecho2 = new Hecho(
        "Serio incidente: Accidente con maquinaria industrial en Chos Malal, Neuquén",
        "Un grave accidente con maquinaria industrial se registró en Chos Malal, Neuquén.",
        new Categoria("ACCIDENTE_CON_MAQUINARIA"),
        new Ubicacion(-37.345571, -70.241485),
        LocalDate.of(2001, 8, 16),
        LocalDate.now(),
        Origen.CARGA_MANUAL
    );

    // Criterios de prueba
    CriterioPorCategoria criterioCategoria = new CriterioPorCategoria(new Categoria("CAIDA_DE_AERONAVE"));
    CriterioPorFecha criterioFecha = new CriterioPorFecha(LocalDate.of(2000, 1, 1), LocalDate.of(2020, 12, 31));

    // Construcción de la colección
    ColeccionBuilder builder = new ColeccionBuilder();
    Coleccion coleccion = builder
        .iniciarCon("Colección prueba", "Esto es una prueba", null, Arrays.asList(criterioCategoria, criterioFecha))
        .agregarHecho(hecho1)
        .agregarHecho(hecho2)
        .build();

    // Validación
    List<Hecho> hechos = coleccion.getHechos();
    assertEquals(2, hechos.size());
    assertEquals("Caída de aeronave impacta en Olavarría", hechos.get(0).getTitulo());
    assertEquals("Serio incidente: Accidente con maquinaria industrial en Chos Malal, Neuquén", hechos.get(1).getTitulo());
  }

  @Test
  public void testCriteriosDePertenencia() {
    // Datos de prueba
    Hecho hecho1 = new Hecho(
        "Caída de aeronave impacta en Olavarría",
        "Grave caída de aeronave ocurrió en las inmediaciones de Olavarría, Buenos Aires.",
        new Categoria("CAIDA_DE_AERONAVE"),
        new Ubicacion(-36.868375, -60.343297),
        LocalDate.of(2001, 11, 29),
        LocalDate.now(),
        Origen.CARGA_MANUAL
    );

    Hecho hecho2 = new Hecho(
        "Serio incidente: Accidente con maquinaria industrial en Chos Malal, Neuquén",
        "Un grave accidente con maquinaria industrial se registró en Chos Malal, Neuquén.",
        new Categoria("ACCIDENTE_CON_MAQUINARIA"),
        new Ubicacion(-37.345571, -70.241485),
        LocalDate.of(2001, 8, 16),
        LocalDate.now(),
        Origen.CARGA_MANUAL
    );

    Hecho hecho3 = new Hecho(
        "Caída de aeronave impacta en Venado Tuerto, Santa Fe",
        "Grave caída de aeronave ocurrió en las inmediaciones de Venado Tuerto, Santa Fe.",
        new Categoria("CAIDA_DE_AERONAVE"),
        new Ubicacion(-33.768051, -61.921032),
        LocalDate.of(2008, 8, 8),
        LocalDate.now(),
        Origen.CARGA_MANUAL
    );

    Hecho hecho4 = new Hecho(
        "Accidente en paso a nivel deja múltiples daños en Pehuajó, Buenos Aires",
        "Grave accidente en paso a nivel ocurrió en las inmediaciones de Pehuajó, Buenos Aires.",
        new Categoria("ACCIDENTE_EN_PASO_A_NIVEL"),
        new Ubicacion(-35.855811, -61.940589),
        LocalDate.of(2020, 1, 27),
        LocalDate.now(),
        Origen.CARGA_MANUAL
    );

    // Construcción inicial de la colección
    ColeccionBuilder builder = new ColeccionBuilder();
    Coleccion coleccion = builder
        .iniciarCon("Colección prueba", "Esto es una prueba", null, List.of())
        .agregarHecho(hecho1)
        .agregarHecho(hecho2)
        .agregarHecho(hecho3)
        .agregarHecho(hecho4)
        .build();

    // Validación inicial
    assertEquals(4, coleccion.getHechos().size());

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
}