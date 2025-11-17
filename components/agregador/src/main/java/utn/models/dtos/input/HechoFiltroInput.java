package utn.models.dtos.input;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HechoFiltroInput {
    private String categoria;
    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;


}