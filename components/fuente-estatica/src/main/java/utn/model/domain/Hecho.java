package utn.model.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Hecho {

    private String id;

    private String titulo;
    private final String descripcion;
    private Categoria categoria;
    private Ubicacion ubicacion;
    private LocalDateTime fecha;
    private LocalDateTime fechaDeCarga;
    private LocalDateTime created_at;
}
