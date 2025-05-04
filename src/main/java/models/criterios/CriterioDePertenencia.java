package models.criterios;

import models.domain.Hecho;

public interface CriterioDePertenencia {
  boolean cumple(Hecho hecho);
}