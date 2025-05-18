package utn.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HechoDTO {
    private String titulo;
    private String descripcion;
    private String categoria;
    private double latitud;
    private double longitud;
    private LocalDateTime fecha_hecho;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}