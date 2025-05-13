package utn.services;

import org.springframework.stereotype.Service;
import utn.models.dtos.output.HechoOutputDTO;

import java.time.LocalDate;
import java.util.List;

@Service
public class HechosService  {


    public List<HechoOutputDTO> filtrarHechos(String categoria, LocalDate fechaReporteDesde, LocalDate fechaReporteHasta, LocalDate fechaAcontecimientoDesde, LocalDate fechaAcontecimientoHasta, String ubicacion) {
        return List.of();
    }
}
