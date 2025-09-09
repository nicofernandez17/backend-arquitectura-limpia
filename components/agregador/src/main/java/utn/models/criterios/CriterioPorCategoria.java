package utn.models.criterios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import utn.models.domain.Hecho;
import utn.models.helpers.Categoria;

@NoArgsConstructor
@Entity
@DiscriminatorValue("CATEGORIA")
public class CriterioPorCategoria extends ICriterioDePertenencia {

  @ManyToOne
  private Categoria categoria;

  public CriterioPorCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return hecho.getCategoria().getNombre().equals(categoria.getNombre());
  }
}
