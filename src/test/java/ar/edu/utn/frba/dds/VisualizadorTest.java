package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Hecho;
import domain.SolicitudEliminacion;
import helpers.EstadoSolicitud;
import org.junit.jupiter.api.Test;
import usuarios.Visualizador;

class VisualizadorTest {

  @Test
  void solicitarEliminacionConHechoComplejoDevuelveSolicitudConHechoYMotivo() {
    Visualizador visualizador = new Visualizador();
    Hecho hecho = new Hecho(
        "Tormenta eléctrica y granizo afectan a Rosario, Santa Fe",
        "Una tormenta eléctrica acompañada de granizo de gran magnitud impactó en Rosario, Santa Fe. El incidente provocó cortes de energía, daños en viviendas y vehículos, y dejó a varias familias evacuadas. Equipos de emergencia trabajan para restablecer los servicios básicos y brindar asistencia a los damnificados.",
        null, null, null, null, null
    );
    String motivo = "Motivo de prueba";

    SolicitudEliminacion solicitud = visualizador.solicitarEliminacion(hecho, motivo);

    assertEquals(hecho, solicitud.getHecho());
    assertEquals(motivo, solicitud.getDescripcion());
    assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());

    solicitud.aceptar();
    assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstado());

    solicitud.rechazar();
    assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado());
  }
}