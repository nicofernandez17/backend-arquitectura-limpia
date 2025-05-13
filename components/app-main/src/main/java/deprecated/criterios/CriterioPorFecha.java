package deprecated.criterios;

import utn.models.domain.Hecho;
import java.time.LocalDate;

public class CriterioPorFecha implements CriterioDePertenencia {
  private final LocalDate desde;
  private final LocalDate hasta;

  public CriterioPorFecha(LocalDate desde, LocalDate hasta) {
    this.desde = desde;
    this.hasta = hasta;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return !hecho.getFecha().isBefore(desde) && !hecho.getFecha().isAfter(hasta);
  }
}