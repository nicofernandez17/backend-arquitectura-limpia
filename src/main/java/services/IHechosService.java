package services;

import models.dtos.output.HechoOutputDTO;

import java.time.LocalDate;
import java.util.List;

public interface IHechosService {

    List<HechoOutputDTO> filtrarHechos(String categoria, LocalDate fechaReporteDesde, LocalDate fechaReporteHasta, LocalDate fechaAcontecimientoDesde, LocalDate fechaAcontecimientoHasta, String ubicacion);
}
