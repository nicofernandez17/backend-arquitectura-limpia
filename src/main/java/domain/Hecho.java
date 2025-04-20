package domain;

import helpers.Categoria;
import helpers.Contenido;
import helpers.Origen;
import helpers.Ubicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;

public class Hecho {

    public Hecho(String descripcionDelHecho) {
        this.descripcion = descripcionDelHecho;
    }

    @Getter
    private String titulo;
    @Getter
    private String descripcion;
    @Getter
    private Categoria categoria;
    @Getter
    private Ubicacion ubicacion;
    @Getter
    private LocalDate fecha; //CAMBIAR
    private LocalDate fechaDeCarga;
    private Contenido contenidoMultimedia;
    private Origen origen;
    @Getter
    private List<String> etiquetas;
    @Getter
    private boolean eliminado;

    public Hecho(String titulo, String descripcion, Categoria categoria, Ubicacion ubicacion, LocalDate fecha, LocalDate fechaDeCarga, Origen origen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.fechaDeCarga = fechaDeCarga;
        this.origen = origen;
    }

    public Hecho(String titulo, String descripcion, Categoria categoria, Ubicacion ubicacion, LocalDate fecha, LocalDate fechaDeCarga, Origen origen, List<String> etiquetas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.fechaDeCarga = fechaDeCarga;
        this.origen = origen;
        this.etiquetas =  new ArrayList<>(etiquetas);
    }

    public void agregarEtiqueta(String etiqueta) {
        if (!etiquetas.contains(etiqueta)) {
            etiquetas.add(etiqueta);
        }
    }

    public void marcarComoEliminado() {
        this.eliminado = true;
    }

    public boolean puedeAgregarseAColeccion() {
        return !eliminado;
    }
}
