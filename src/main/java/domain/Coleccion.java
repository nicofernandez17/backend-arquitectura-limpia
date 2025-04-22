package domain;

import criterios.CriterioDePertenencia;
import fuentes.FuenteDatos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class Coleccion {


  // Getters y setters
  private List<Hecho> hechos;
  private final FuenteDatos fuente;
  @Getter
  private final String titulo;
  @Getter
  private final String descripcion;
  private final List<CriterioDePertenencia> criteriosDePertenencia;

  public Coleccion(String titulo, String descripcion, FuenteDatos fuente,
                   List<CriterioDePertenencia> criterios) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criteriosDePertenencia = criterios != null ? new ArrayList<>(criterios) : new ArrayList<>();
    this.hechos = new ArrayList<>();
  }


  public void agregarHecho(Hecho hecho) {
    if (criteriosDePertenencia.stream().allMatch(criterio -> criterio.cumple(hecho))) {
      this.hechos.add(hecho);
    }
  }


  public void cargarColeccion() {
    // Aplica todos los criterios para filtrar los hechos
    this.hechos = fuente.obtenerHechos().stream()
        .filter(hecho -> this.criteriosDePertenencia.stream()
            .allMatch(criterio -> criterio.cumple(hecho)))
        .collect(Collectors.toList());
  }

  private boolean cumpleTodosLosCriterios(Hecho hecho) {
    return criteriosDePertenencia.stream().allMatch(c -> c.cumple(hecho));
  }

  public List<Hecho> getHechos() {
    return Collections.unmodifiableList(hechos);
  }

  public void agregarCriterio(CriterioDePertenencia criterio) {
    this.criteriosDePertenencia.add(criterio);
  }

  public List<CriterioDePertenencia> getCriteriosDePertenencia() {
    return Collections.unmodifiableList(criteriosDePertenencia);
  }
}