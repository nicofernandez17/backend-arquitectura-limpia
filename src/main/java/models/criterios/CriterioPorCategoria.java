package models.criterios;

import models.domain.Hecho;
import models.helpers.Categoria;

public class CriterioPorCategoria implements CriterioDePertenencia {
  private final Categoria categoria;

  public CriterioPorCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return hecho.getCategoria().getNombre().equals(categoria.getNombre());
  }
}
