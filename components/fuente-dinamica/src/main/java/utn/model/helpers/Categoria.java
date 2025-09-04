package utn.model.helpers;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Categoria {


  private String nombre;

  public Categoria(String categoriaNombre) {
    this.nombre = categoriaNombre;
  }


}
