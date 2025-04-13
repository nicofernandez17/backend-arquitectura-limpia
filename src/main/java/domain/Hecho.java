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
    private LocalDate fecha; //CAMBIAR
    private LocalDate fechaDeCarga;
    private Contenido contenidoMultimedia;
    private Origen origen;
    private List<String> tags;

    public Hecho(String titulo, String descripcion, Categoria categoria, Ubicacion ubicacion, LocalDate fecha, LocalDate fechaDeCarga, Origen origen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.fechaDeCarga = fechaDeCarga;
        this.origen = origen;
    }


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
