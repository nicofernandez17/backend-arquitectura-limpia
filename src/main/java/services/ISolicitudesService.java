package services;

import models.dtos.input.SolicitudInputDTO;
import models.dtos.output.SolicitudOutputDTO;

public interface ISolicitudesService {
    public SolicitudOutputDTO crearSolicitud(SolicitudInputDTO solicitudDTO);
}
