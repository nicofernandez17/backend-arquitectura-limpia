package utn.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HechoDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String categoria;
    private Double latitud;
    private Double longitud;
    private LocalDateTime fecha_hecho;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private byte[] archivoContenido;
    private String archivoNombre;
    //TODO
}
