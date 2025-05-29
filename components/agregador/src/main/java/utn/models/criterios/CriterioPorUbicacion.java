package utn.models.criterios;

import utn.models.domain.Hecho;
import utn.models.helpers.Ubicacion;

public class CriterioPorUbicacion implements ICriterioDePertenencia {
  private final Ubicacion ubicacion;

  public CriterioPorUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    if (hecho.getUbicacion() == null || ubicacion == null) return false;
    return Double.compare(hecho.getUbicacion().getLatitud(), ubicacion.getLatitud()) == 0
        && Double.compare(hecho.getUbicacion().getLongitud(), ubicacion.getLongitud()) == 0;
  }
}
