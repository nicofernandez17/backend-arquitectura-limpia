package utn.models.domain;

import lombok.Data;
import lombok.Getter;
import utn.models.criterios.CriterioDePertenencia;
import utn.models.dtos.HechoDTO;
import utn.services.FuenteService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Coleccion {

  private String id;
  // Getters y setters
  private List<Hecho> hechos;
  private List<FuenteService> fuentes;
  @Getter
  private final String titulo;
  @Getter
  private final String descripcion;
  private  List<CriterioDePertenencia> criteriosDePertenencia;

    public Coleccion(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }



  public void agregarHecho(Hecho hecho) {
    if (criteriosDePertenencia.stream().allMatch(criterio -> criterio.cumple(hecho))) {
      this.hechos.add(hecho);
    }
  }

  public void actualizarHechos() {
    hechos = fuentes.stream()
            .flatMap(fuente -> fuente.obtenerHechos().stream())
            .collect(Collectors.toList());
  }

  public List<Hecho> getHechos() {
    return Collections.unmodifiableList(hechos);
  }

}