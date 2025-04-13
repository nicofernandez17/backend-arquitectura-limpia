package domain;

import helpers.Categoria;
import helpers.Contenido;
import helpers.Origen;
import helpers.Ubicacion;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Hecho {

    private String titulo;
    private String descripcion;
    private Categoria categoria;
    private Ubicacion ubicacion;
    private LocalDate fecha;
    private Date fechaDeCarga;
    private Contenido contenidoMultimedia;
    private Origen origen;
    private List<String> tags;


    public Categoria getCategoria() {
        return categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
