package utn.model.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Hecho {

    private String id;

    private String titulo;
    private final String descripcion;
    private Categoria categoria;
    private Ubicacion ubicacion;
    private LocalDate fecha;
    private LocalDate fechaDeCarga;
}
