package utn.model.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Entity
@Table(name = "colleccion")
public class Coleccion {
  //----------------------------------ATRIBUTOS-----------------------------------------------//
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id; //TODO - Cambiar a long o Int; No se porque string

  @Builder.Default
  @OneToMany(mappedBy = "colleccion")
  @JoinColumn(name = "hecho_id", referencedColumnName = "id")
  private List<Hecho> hechos = new ArrayList<>();


  @Column(name = "titulo")
  private String titulo;

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  public Coleccion() {

  }


  //----------------------------------METODOS-----------------------------------------------//







  public void agregarHechos(List<Hecho> hechos) {
    this.hechos.addAll(hechos);
  }

}
