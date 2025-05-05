package utn.models.criterios;

import utn.models.domain.Hecho;
import utn.models.helpers.Ubicacion;

public class CriterioPorUbicacion implements CriterioDePertenencia {
  private final Ubicacion ubicacion;

  public CriterioPorUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return hecho.getUbicacion().equals(ubicacion);
  }
}
