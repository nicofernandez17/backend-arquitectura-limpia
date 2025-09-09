package utn.model.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder

public class Coleccion {

  private String id;


  private List<Hecho> hechos = new ArrayList<>();


  private String titulo;

  private String descripcion;

  public Coleccion() {

  }


  //----------------------------------METODOS-----------------------------------------------//







  public void agregarHechos(List<Hecho> hechos) {
    this.hechos.addAll(hechos);
  }

}
