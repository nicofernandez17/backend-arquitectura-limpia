package utn.models.domain;

import jakarta.persistence.*;
import lombok.*;
import utn.models.helpers.EstadoRevision;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "revision")
public class Revision {

    @Id
    private Long id;
    private String idHecho;
    
    @Transient
    private Hecho contenidoPropuesto;
    private String comentarioModerador;

    @Enumerated(EnumType.STRING)
    private EstadoRevision estado;
    private LocalDateTime fechaRevision;

    public Revision(Hecho contenidoPropuesto,String idHecho) {
        this.contenidoPropuesto = contenidoPropuesto;
        this.idHecho = idHecho;
        this.estado = EstadoRevision.PENDIENTE;
        this.fechaRevision = LocalDateTime.now();
    }


}
