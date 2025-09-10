package utn.models.domain;

import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

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
