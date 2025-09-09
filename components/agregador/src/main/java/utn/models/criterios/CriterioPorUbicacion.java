package utn.models.criterios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import utn.models.domain.Hecho;
import utn.models.helpers.Ubicacion;

@NoArgsConstructor
@Entity
@DiscriminatorValue("UBICACION")
public class CriterioPorUbicacion extends ICriterioDePertenencia {

  @Embedded
  private Ubicacion ubicacion;

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
