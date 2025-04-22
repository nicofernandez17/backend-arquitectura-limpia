package ar.edu.utn.frba.dds;

import solicitudes.SolicitudEliminacion;
import domain.Hecho;
import helpers.Categoria;
import helpers.EstadoSolicitud;
import helpers.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuarios.Contribuyente;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class Escenario3Test {

  Hecho hecho;
  Contribuyente contribuyente;

  @BeforeEach
  public void init() {
    hecho = new Hecho("Brote de enfermedad contagiosa causa estragos en San Lorenzo, Santa Fe", "Grave brote de enfermedad contagiosa ocurrió en las inmediaciones de San Lorenzo, Santa Fe. El incidente dejó varios heridos y daños materiales. Se ha declarado estado de emergencia en la región para facilitar la asistencia.", new Categoria("Evento sanitario"), new Ubicacion(-32.786098, -60.741543), LocalDate.of(2005, 7, 5), LocalDate.now(), null);

    contribuyente = new Contribuyente("Juan", "Pérez", 30, null);
  }

  @Test
  public void testSolicitudesDeEliminacion() {
    String motivo = "Este hecho contiene información sensible y debe ser eliminado por razones de privacidad.".repeat(10);

    // Crear la primera solicitud de eliminación a través del contribuyente
    SolicitudEliminacion solicitud1 = contribuyente.solicitarEliminacion(hecho, motivo);

    // Verificar que la solicitud está en estado pendiente
    assertEquals(EstadoSolicitud.PENDIENTE, solicitud1.getEstado());

    // Rechazar la solicitud un día después
    solicitud1.rechazar();
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud1.getEstado());
    assertTrue(hecho.puedeAgregarseacoleccion());

    // Crear una segunda solicitud de eliminación a través del contribuyente
    SolicitudEliminacion solicitud2 = contribuyente.solicitarEliminacion(hecho, motivo);

    // Aceptar la solicitud después
    solicitud2.aceptar();
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud2.getEstado());
    assertFalse(hecho.puedeAgregarseacoleccion());
  }

  @Test
  public void testMotivoInvalido() {
    String motivoCorto = "Motivo muy corto";

    // Intentar crear una solicitud con un motivo inválido
    assertThrows(IllegalArgumentException.class, () -> contribuyente.solicitarEliminacion(hecho, motivoCorto));
  }

  @Test
  public void testEstadoInicialPendiente() {
    String motivo = "Motivo válido".repeat(50);

    // Crear una solicitud de eliminación válida
    SolicitudEliminacion solicitud = contribuyente.solicitarEliminacion(hecho, motivo);

    // Verificar que el estado inicial es pendiente
    assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
  }

  @Test
  public void testRechazarSolicitudYaRechazada() {
    String motivo = "Motivo válido".repeat(50);

    // Crear una solicitud de eliminación válida
    SolicitudEliminacion solicitud = contribuyente.solicitarEliminacion(hecho, motivo);

    // Rechazar la solicitud
    solicitud.rechazar();
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado());

    // Intentar rechazar nuevamente
    solicitud.rechazar();
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado()); // No debe cambiar
  }

  @Test
  public void testAceptarSolicitudYaAceptada() {
    String motivo = "Motivo válido".repeat(50);

    // Crear una solicitud de eliminación válida
    SolicitudEliminacion solicitud = contribuyente.solicitarEliminacion(hecho, motivo);

    // Aceptar la solicitud
    solicitud.aceptar();
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado());

    // Intentar aceptar nuevamente
    solicitud.aceptar();
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado()); // No debe cambiar
  }
}