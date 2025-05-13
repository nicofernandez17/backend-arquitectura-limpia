package deprecated.criterios;

import utn.models.domain.Hecho;

public interface CriterioDePertenencia {
  boolean cumple(Hecho hecho);
}