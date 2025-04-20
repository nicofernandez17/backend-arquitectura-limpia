package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Solicitudes.SolicitudEliminacion;
import domain.Hecho;
import helpers.Categoria;
import helpers.EstadoSolicitud;
import helpers.Ubicacion;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class Escenario3Test {

  @Test
  public void testSolicitudesDeEliminacion() {
    // Crear el hecho
    Hecho hecho = new Hecho(
        "Brote de enfermedad contagiosa causa estragos en San Lorenzo, Santa Fe",
        "Grave brote de enfermedad contagiosa ocurrió en las inmediaciones de San Lorenzo, Santa Fe. El incidente dejó varios heridos y daños materiales. Se ha declarado estado de emergencia en la región para facilitar la asistencia.",
        new Categoria("Evento sanitario"),
        new Ubicacion(-32.786098, -60.741543),
        LocalDate.of(2005, 7, 5),
        LocalDate.now(),
        null
    );

    // Crear la primera solicitud de eliminación
    String motivo = "Este hecho contiene información sensible y debe ser eliminado por razones de privacidad.".repeat(10);
    SolicitudEliminacion solicitud1 = new SolicitudEliminacion(hecho, motivo);

    // Verificar que la solicitud está en estado pendiente
    assertEquals(EstadoSolicitud.PENDIENTE, solicitud1.getEstado());

    // Rechazar la solicitud un día después
    solicitud1.rechazar();
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud1.getEstado());
    assertTrue(hecho.puedeAgregarseAColeccion());

    // Crear una segunda solicitud de eliminación
    SolicitudEliminacion solicitud2 = new SolicitudEliminacion(hecho, motivo);

    // Aceptar la solicitud  después
    solicitud2.aceptar();
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud2.getEstado());
    assertFalse(hecho.puedeAgregarseAColeccion());
  }

  @Test
  public void testMotivoInvalido() {
    Hecho hecho = new Hecho(
        "Título de prueba",
        "Descripción de prueba",
        new Categoria("Prueba"),
        new Ubicacion(0.0, 0.0),
        LocalDate.now(),
        LocalDate.now(),
        null
    );

    String motivoCorto = "Motivo muy corto";
    assertThrows(IllegalArgumentException.class, () -> new SolicitudEliminacion(hecho, motivoCorto));
  }

  @Test
  public void testEstadoInicialPendiente() {
    Hecho hecho = new Hecho(
        "Título de prueba",
        "Descripción de prueba",
        new Categoria("Prueba"),
        new Ubicacion(0.0, 0.0),
        LocalDate.now(),
        LocalDate.now(),
        null
    );

    String motivo = "Motivo válido".repeat(50);
    SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, motivo);

    assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
  }

  @Test
  public void testRechazarSolicitudYaRechazada() {
    Hecho hecho = new Hecho(
        "Título de prueba",
        "Descripción de prueba",
        new Categoria("Prueba"),
        new Ubicacion(0.0, 0.0),
        LocalDate.now(),
        LocalDate.now(),
        null
    );

    String motivo = "Motivo válido".repeat(50);
    SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, motivo);

    solicitud.rechazar();
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado());

    solicitud.rechazar(); // Intentar rechazar nuevamente
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado()); // No debe cambiar
  }

  @Test
  public void testAceptarSolicitudYaAceptada() {
    Hecho hecho = new Hecho(
        "Título de prueba",
        "Descripción de prueba",
        new Categoria("Prueba"),
        new Ubicacion(0.0, 0.0),
        LocalDate.now(),
        LocalDate.now(),
        null
    );

    String motivo = "Motivo válido".repeat(50);
    SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, motivo);

    solicitud.aceptar();
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado());

    solicitud.aceptar(); // Intentar aceptar nuevamente
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado()); // No debe cambiar
  }
}