package utn.models.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.criterios.ICriterioDePertenencia;
import utn.models.helpers.ConsensoNivel;
import utn.models.helpers.FuenteNombre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Coleccion {

  private String id;

  @Builder.Default
  private List<Hecho> hechos = new ArrayList<>();

  private IAlgoritmoConsenso algoritmo;
  private String titulo;

  private String descripcion;
  private List<ICriterioDePertenencia> criteriosDePertenencia;

  @Builder.Default
  private List<FuenteNombre> fuentes = new ArrayList<>();

  public void agregarHecho(Hecho hecho) {
    if (criteriosDePertenencia == null || criteriosDePertenencia.isEmpty() ||
            criteriosDePertenencia.stream().allMatch(criterio -> criterio.cumple(hecho))) {
      this.hechos.add(hecho);
    }
  }

  public void actualizarHechos(List<Hecho> hechos) {
    if (hechos == null) {
      this.hechos = new ArrayList<>();
      return;
    }

    if (criteriosDePertenencia == null || criteriosDePertenencia.isEmpty()) {
      this.hechos = new ArrayList<>(hechos);
    } else {
      this.hechos = hechos.stream()
              .filter(hecho -> criteriosDePertenencia.stream()
                      .allMatch(criterio -> criterio.cumple(hecho)))
              .collect(Collectors.toList());
    }
  }

  public List<Hecho> getHechos() {
    return hechos.stream()
            .filter(hecho -> !hecho.isEliminado())
            .toList();
  }

  public List<Hecho> getHechosFiltradosPorConsenso() {
    // Si no hay algoritmo, no se puede filtrar por consenso → devolver todos los hechos válidos
    if (algoritmo == null) {
      return getHechos(); // ya filtra los eliminados
    }

    // Si hay algoritmo, aplicar filtro por nivel mínimo de consenso
    return hechos.stream()
            .filter(hecho -> !hecho.isEliminado())
            .filter(hecho -> hecho.getConsensoNivel().getPrioridad() >= algoritmo.getNivelQueAplica().getPrioridad())
            .toList();
  }

  public void agregarHechos(List<Hecho> hechos) {
    this.hechos.addAll(hechos);
  }

}
