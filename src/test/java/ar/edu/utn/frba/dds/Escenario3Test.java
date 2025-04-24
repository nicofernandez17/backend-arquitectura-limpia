package ar.edu.utn.frba.dds;

import solicitudes.SolicitudEliminacion;
import domain.Hecho;
import helpers.Categoria;
import helpers.EstadoSolicitud;
import helpers.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuarios.Administrador;
import usuarios.Contribuyente;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class Escenario3Test {

  private Hecho hecho;
  private Contribuyente contribuyente;


  @BeforeEach
  public void init() {
    hecho = new Hecho(
        "Brote de enfermedad contagiosa causa estragos en San Lorenzo, Santa Fe",
        "Grave brote de enfermedad contagiosa ocurrió en las inmediaciones de San Lorenzo, Santa Fe. El incidente dejó varios heridos y daños materiales. Se ha declarado estado de emergencia en la región para facilitar la asistencia.",
        new Categoria("Evento sanitario"),
        new Ubicacion(-32.786098, -60.741543),
        LocalDate.of(2005, 7, 5),
        LocalDate.now(),
        null
    );

    contribuyente = new Contribuyente("Juan", "Pérez", 30, null);

  }

  @Test
  public void testAdministradorAceptaSolicitud() {
    String motivo = "Este hecho contiene información sensible y debe ser eliminado por razones de privacidad.".repeat(10);

    // Crear solicitud
    SolicitudEliminacion solicitud = hecho.solicitarEliminacion(contribuyente, motivo);

    // Verificar estado inicial
    assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());

    // Administrador acepta la solicitud
    solicitud.aceptar();

    // Verificar estado final
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado());
    assertFalse(hecho.puedeAgregarseacoleccion());
  }

  @Test
  public void testAdministradorRechazaSolicitud() {
    String motivo = "Este hecho contiene información sensible y debe ser eliminado por razones de privacidad.".repeat(10);

    // Crear solicitud
    SolicitudEliminacion solicitud = hecho.solicitarEliminacion(contribuyente, motivo);

    // Verificar estado inicial
    assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());

    // Administrador rechaza la solicitud
    solicitud.rechazar();

    // Verificar estado final
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado());
    assertTrue(hecho.puedeAgregarseacoleccion());
  }

  @Test
  public void testMotivoInvalidoLanzaExcepcion() {
    String motivoCorto = "Motivo muy corto";

    // Intentar crear una solicitud con un motivo inválido
    assertThrows(IllegalArgumentException.class, () -> hecho.solicitarEliminacion(contribuyente, motivoCorto));
  }

  @Test
  public void testEstadoInicialEsPendiente() {
    String motivo = "Motivo válido".repeat(50);

    // Crear solicitud
    SolicitudEliminacion solicitud = hecho.solicitarEliminacion(contribuyente, motivo);

    // Verificar estado inicial
    assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
  }

  @Test
  public void testRechazarSolicitudYaRechazadaNoCambiaEstado() {
    String motivo = "Motivo válido".repeat(50);

    // Crear solicitud
    SolicitudEliminacion solicitud = hecho.solicitarEliminacion(contribuyente, motivo);

    // Rechazar solicitud
    solicitud.rechazar();
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado());

    // Intentar rechazar nuevamente
    solicitud.rechazar();
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado());
  }

  @Test
  public void testAceptarSolicitudYaAceptadaNoCambiaEstado() {
    String motivo = "Motivo válido".repeat(50);

    // Crear solicitud
    SolicitudEliminacion solicitud = hecho.solicitarEliminacion(contribuyente, motivo);

    // Aceptar solicitud
    solicitud.aceptar();
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado());

    // Intentar aceptar nuevamente
    solicitud.aceptar();
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado());
  }
}