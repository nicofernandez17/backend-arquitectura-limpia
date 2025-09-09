package utn.models.criterios;

import jakarta.persistence.*;
import utn.models.domain.Hecho;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class ICriterioDePertenencia {

  private String nombre;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  public abstract boolean cumple(Hecho hecho);


}