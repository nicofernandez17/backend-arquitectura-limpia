package utn.models.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.models.helpers.EstadoSolicitud;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@NoArgsConstructor
@Entity @Table(name = "solicitud_eliminacion")
@Data
public class SolicitudEliminacion {
  //----------------------------------ATRIBUTOS-----------------------------------------------//

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "fecha_creacion", columnDefinition = "DATE")
  private LocalDateTime fechaCreacion;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado_solicitud")
  private EstadoSolicitud estado;

  @ManyToOne
  @JoinColumn(name = "hecho_id", referencedColumnName = "id")
  private Hecho hecho;

  @Column(name = "motivo", columnDefinition = "TEXT")
  private String motivo;

  //----------------------------------METODOS-----------------------------------------------//

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

  }
}
