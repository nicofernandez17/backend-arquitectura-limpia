package utn.models.domain;

import utn.models.helpers.EstadoSolicitud;
import java.time.LocalDateTime;
import lombok.Getter;
import utn.models.usuarios.Contribuyente;

public class SolicitudEliminacion {
  @Getter
  private final Contribuyente usuario;
  @Getter
  private final LocalDateTime fechaCreacion;
  @Getter
  private EstadoSolicitud estado;
  @Getter
  private final Hecho hecho;
  @Getter
  private final String motivo;

  public SolicitudEliminacion(Hecho hecho, String motivo, Contribuyente usuario) {
      this.usuario = usuario;
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
