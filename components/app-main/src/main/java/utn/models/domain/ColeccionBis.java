package utn.models.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

public class ColeccionBis {

    private String id;
    // Getters y setters
    private List<Hecho> hechos;

    @Getter
    private final String titulo;
    @Getter
    private final String descripcion;


    public ColeccionBis(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hechos = new ArrayList<>();
    }

    public List<Hecho> getHechos() {
        return Collections.unmodifiableList(hechos);
    }




}
