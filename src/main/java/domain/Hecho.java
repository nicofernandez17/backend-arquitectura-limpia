package domain;

import helpers.Contenido;
import helpers.Origen;
import helpers.Ubicacion;

import java.util.Date;
import java.util.List;

public class Hecho {

    private String titulo;
    private String descripcion;
    private String categoria;
    private Ubicacion ubicacion;
    private Date fecha;
    private Date fechaDeCarga;
    private Contenido contenidoMultimedia;
    private Origen origen;
    private List<String> tags;


    public String getTitulo() {
        return titulo;
    }
}
