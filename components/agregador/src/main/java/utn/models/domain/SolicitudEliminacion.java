package utn.models.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utn.models.helpers.EstadoSolicitud;

import java.time.LocalDateTime;

@Data
public class SolicitudEliminacion {


  private final LocalDateTime fechaCreacion;

  private EstadoSolicitud estado;

  private final Hecho hecho;


  private final String motivo;

  public SolicitudEliminacion(Hecho hecho, String motivo) {

      if (motivo == null || motivo.length() < 20) {
      throw new IllegalArgumentException("El motivo debe tener al menos 20 caracteres.");
    }
    this.hecho = hecho;
    this.motivo = motivo;
    this.fechaCreacion = LocalDateTime.now();
    this.estado = EstadoSolicitud.PENDIENTE;
  }

  public void aceptar() {
    this.estado = EstadoSolicitud.ACEPTADA;
    this.hecho.marcarComoEliminado();
  }


  public void rechazar() {
    estado = EstadoSolicitud.RECHAZADA;
    //Eliminar Fecha
  }
}
