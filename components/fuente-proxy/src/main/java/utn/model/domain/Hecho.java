package utn.model.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Hecho {
    private String titulo;
    private String descripcion;
    private Categoria categoria;
    private Ubicacion ubicacion;
    private LocalDateTime fecha;
    private LocalDateTime fechaDeCarga;
    private LocalDateTime fechaUltimaActualizacion;
}
