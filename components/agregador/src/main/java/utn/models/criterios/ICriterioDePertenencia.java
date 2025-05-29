package utn.models.criterios;

import utn.models.domain.Hecho;

public interface ICriterioDePertenencia {
  boolean cumple(Hecho hecho);
}