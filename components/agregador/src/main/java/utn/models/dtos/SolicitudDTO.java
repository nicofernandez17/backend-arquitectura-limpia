package utn.models.dtos;

import lombok.Builder;
import lombok.Data;
import utn.models.domain.Hecho;
import utn.models.helpers.EstadoSolicitud;

import java.time.LocalDateTime;

@Data
@Builder
public class SolicitudDTO {
    private LocalDateTime fechaCreacion;
    private String estado;

    private String hecho;


    private final String motivo;

}
