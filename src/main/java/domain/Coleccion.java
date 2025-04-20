package domain;

import criterios.CriterioDePertenencia;

import criterios.CriterioPorFecha;
import filtros.FiltroHechos;
import fuentes.FuenteDatos;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Coleccion {


    // Getters y setters
    @Getter
    private List<Hecho> hechos;
    private FuenteDatos fuente;
    private String titulo;
    private String descripcion;


    @Getter
    private List<CriterioDePertenencia> criteriosDePertenencia;

    public Coleccion(String titulo, String descripcion, FuenteDatos fuente, List<CriterioDePertenencia> criterios) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fuente = fuente;
        this.criteriosDePertenencia = new ArrayList<>(criterios);
        this.hechos = new ArrayList<>();
    }

    public void agregarHecho(Hecho hecho) {
        this.hechos.add(hecho);
    }


    public void cargarColeccion() {
        // Aplica todos los criterios para filtrar los hechos
        this.hechos = this.hechos.stream()
            .filter(hecho -> this.criteriosDePertenencia.stream().allMatch(criterio -> criterio.cumple(hecho)))
            .collect(Collectors.toList());
    }

    //TODO Se puede cambiar por un getter
    public List<Hecho> leerColeccion() {
        List<Hecho> todosLosHechos = fuente.obtenerHechos();
        return todosLosHechos.stream()
            .filter(this::cumpleTodosLosCriterios)
            .collect(Collectors.toList());
    }

    private boolean cumpleTodosLosCriterios(Hecho hecho) {
        return criteriosDePertenencia.stream().allMatch(c -> c.cumple(hecho));
    }

    public void agregarCriterio(CriterioDePertenencia criterio) {
        this.criteriosDePertenencia.add(criterio);
    }

    //public List<Hecho> aplicarFiltros(List<FiltroHechos> filtros) {
    //    return this.hechos.stream()
    //        .filter(hecho -> filtros.stream().allMatch(filtro -> filtro.filtrar(hecho)))
    //        .collect(Collectors.toList());
    //}
}