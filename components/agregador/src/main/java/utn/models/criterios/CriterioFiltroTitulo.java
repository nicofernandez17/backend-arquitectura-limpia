package utn.models.criterios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.models.domain.Hecho;

@NoArgsConstructor
@Entity
@DiscriminatorValue("TITULO")
public class CriterioFiltroTitulo extends ICriterioDePertenencia {
  @Getter
  @Setter
  private String texto;

  public CriterioFiltroTitulo(String texto) {
    this.texto = texto;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    if (hecho == null || hecho.getTitulo() == null) {
      return false;
    }
    return hecho.getTitulo().equalsIgnoreCase(texto);
  }
}
