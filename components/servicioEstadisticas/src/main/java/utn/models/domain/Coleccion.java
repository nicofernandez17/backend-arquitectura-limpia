package utn.models.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coleccion")
public class Coleccion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "coleccion_hechos",
          joinColumns = @JoinColumn(name = "coleccion_id"),
          inverseJoinColumns = @JoinColumn(name = "hecho_id")
  )
  @Builder.Default
  private List<Hecho> hechos = new ArrayList<>();


  private String titulo;

  private String descripcion;




  //----------------------------------METODOS-----------------------------------------------//







  public void agregarHechos(List<Hecho> hechos) {
    this.hechos.addAll(hechos);
  }

}
