package utn.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HechoDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String categoria;
    private Double latitud;
    private Double longitud;
    private LocalDateTime fecha_hecho;
    private LocalDateTime created_at;
    //TODO
}
