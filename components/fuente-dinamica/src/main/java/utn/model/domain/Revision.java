package utn.model.domain;

import lombok.Data;
import utn.model.dtos.HechoDTO;
import utn.model.helpers.EstadoRevision;

import java.time.LocalDateTime;

@Data
public class Revision {

    private Long id;
    private String idHecho;
    private HechoDTO contenidoPropuesto;
    private String comentarioModerador;
    private EstadoRevision estado;
    private LocalDateTime fechaRevision;

    public Revision(HechoDTO contenidoPropuesto,String idHecho) {
        this.contenidoPropuesto = contenidoPropuesto;
        this.idHecho = idHecho;
        this.estado = EstadoRevision.PENDIENTE;
        this.fechaRevision = LocalDateTime.now();
    }

}
