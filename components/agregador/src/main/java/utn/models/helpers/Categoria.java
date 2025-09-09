package utn.models.helpers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity @Table (name = "categoria")
public class Categoria {
  //----------------------------------ATRIBUTOS-----------------------------------------------//
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id; //TODO - Cambiar a long o Int; No se porque string

  @Column(name = "nombre")
  private String nombre;


  //----------------------------------METODOS-----------------------------------------------//
  public Categoria(String categoriaNombre) {
    this.nombre = categoriaNombre;
  }

  public String getNombre() {
    return nombre;
  }
}
