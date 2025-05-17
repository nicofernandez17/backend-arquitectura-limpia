package utn.models.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import utn.models.criterios.CriterioDePertenencia;
import utn.services.fuentes.IFuenteService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Coleccion {

  private String id;
  // Getters y setters
  private List<Hecho> hechos;
  private List<IFuenteService> fuentes;
  @Getter
  private final String titulo;
  @Getter
  private final String descripcion;
  private  List<CriterioDePertenencia> criteriosDePertenencia;

  public Coleccion(String id,String titulo, String descripcion) {
    this.id = id;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.hechos = new ArrayList<>();
    this.fuentes = new ArrayList<>();
    this.criteriosDePertenencia = new ArrayList<>();
  }



  public void agregarHecho(Hecho hecho) {
    if (criteriosDePertenencia.stream().allMatch(criterio -> criterio.cumple(hecho))) {
      this.hechos.add(hecho);
    }
  }

  public void actualizarHechos() {
    this.hechos = fuentes.stream()
            .flatMap(fuente -> fuente.obtenerHechos().stream())
            .collect(Collectors.toList());
  }

  public List<Hecho> getHechos() {
    return hechos.stream()
            .filter(hecho -> !hecho.isEliminado())
            .toList(); // O bien .collect(Collectors.toUnmodifiableList()) si quieres que sea inmodificable
  }

}