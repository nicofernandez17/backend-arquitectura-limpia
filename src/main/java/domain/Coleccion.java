package domain;

import criterios.CriterioDePertenencia;

import fuentes.FuenteDatos;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Coleccion {
    private List<Hecho> hechos;
    private FuenteDatos fuente;
    private String titulo;
    private String descripcion;
    private List<CriterioDePertenencia> criteriosDePertenencia;

    public Coleccion(String titulo, String descripcion, FuenteDatos fuente, List<CriterioDePertenencia> criterios) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fuente = fuente;
        this.criteriosDePertenencia = criterios;
        this.hechos = new ArrayList<>();
    }

    public List<Hecho> leerColeccion() {
        List<Hecho> todosLosHechos = fuente.obtenerHechos();
        return todosLosHechos.stream()
            .filter(this::cumpleTodosLosCriterios)
            .collect(Collectors.toList());
    }

    private boolean cumpleTodosLosCriterios(Hecho hecho) {
        return criteriosDePertenencia.stream().allMatch(c -> c.cumple(hecho));
    }

    // Getters y setters
}