package utn.models.dtos.output;

import java.time.LocalDate;

public class SolicitudOutputDTO {
    private Long id;
    private Long hechoId;
    private String motivo;
    private String emailSolicitante;
    private LocalDate fechaSolicitud;
    private String estado;
}
