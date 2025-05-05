package utn.services;

import utn.models.dtos.input.SolicitudInputDTO;
import utn.models.dtos.output.SolicitudOutputDTO;

public interface ISolicitudesService {
    public SolicitudOutputDTO crearSolicitud(SolicitudInputDTO solicitudDTO);
}
