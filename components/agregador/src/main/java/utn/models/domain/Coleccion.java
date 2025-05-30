package utn.models.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import utn.models.criterios.ICriterioDePertenencia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Coleccion {

  private String id;
  // Getters y setters
  private List<Hecho> hechos;

  @Getter
  private final String titulo;
  @Getter
  private final String descripcion;
  private  List<ICriterioDePertenencia> criteriosDePertenencia;





  public void agregarHecho(Hecho hecho) {
    if (criteriosDePertenencia.stream().allMatch(criterio -> criterio.cumple(hecho))) {
      this.hechos.add(hecho);
    }
  }

  public void actualizarHechos(List<Hecho> hechos) {
    if (hechos == null) {
      this.hechos = new ArrayList<>();
      return;
    }

    if (criteriosDePertenencia == null || criteriosDePertenencia.isEmpty()) {
      this.hechos = new ArrayList<>(hechos); // Todos los hechos se agregan
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
            .toList(); // O bien .collect(Collectors.toUnmodifiableList()) si quieres que sea inmodificable
  }

  public void agregarHechos(List<Hecho> hechos) {
    this.hechos.addAll(hechos);
  }

}