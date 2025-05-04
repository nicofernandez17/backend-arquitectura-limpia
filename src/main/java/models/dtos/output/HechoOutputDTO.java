package models.dtos.output;

import java.time.LocalDate;

public class HechoOutputDTO {
    private Long id;
    private String categoria;
    private LocalDate fechaReporte;
    private LocalDate fechaAcontecimiento;
    private String ubicacion;
    private String descripcion;
    private String fuente;
}
