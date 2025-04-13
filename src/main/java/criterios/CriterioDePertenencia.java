package criterios;

import domain.Hecho;

public interface CriterioDePertenencia {
  boolean cumple(Hecho hecho);
}