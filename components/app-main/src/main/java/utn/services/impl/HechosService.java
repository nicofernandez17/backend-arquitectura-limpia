package utn.services.impl;

import org.springframework.stereotype.Service;
import utn.models.dtos.output.HechoOutputDTO;
import utn.services.IHechosService;

import java.time.LocalDate;
import java.util.List;

@Service
public class HechosService implements IHechosService {

    @Override
    public List<HechoOutputDTO> filtrarHechos(String categoria, LocalDate fechaReporteDesde, LocalDate fechaReporteHasta, LocalDate fechaAcontecimientoDesde, LocalDate fechaAcontecimientoHasta, String ubicacion) {
        return List.of();
    }
}
