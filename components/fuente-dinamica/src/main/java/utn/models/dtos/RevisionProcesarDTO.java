package utn.models.dtos;

import lombok.Data;

@Data
public class RevisionProcesarDTO {
    private boolean aceptado;      // true = aceptar, false = rechazar
    private String comentario;

}