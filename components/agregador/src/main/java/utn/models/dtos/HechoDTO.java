package utn.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HechoDTO {
    private String titulo;
    private String descripcion;
    private String categoria;
    private double latitud;
    private double longitud;
    private String fecha_hecho;
}