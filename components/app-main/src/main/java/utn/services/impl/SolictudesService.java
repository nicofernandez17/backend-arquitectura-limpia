package utn.services.impl;

import org.springframework.stereotype.Service;
import utn.models.dtos.input.SolicitudInputDTO;
import utn.models.dtos.output.SolicitudOutputDTO;
import utn.services.ISolicitudesService;

@Service
public class SolictudesService implements ISolicitudesService {
    @Override
    public SolicitudOutputDTO crearSolicitud(SolicitudInputDTO solicitudDTO) {
        return null;
    }
}
