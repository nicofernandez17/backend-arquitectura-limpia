package models.criterios;

import models.domain.Hecho;
import lombok.Getter;
import lombok.Setter;

public class CriterioFiltroTitulo implements CriterioDePertenencia {
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
