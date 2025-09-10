package utn.models.helpers;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="categoria")
public class Categoria {


  private String nombre;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Categoria(String categoriaNombre) {
    this.nombre = categoriaNombre;
  }



}
