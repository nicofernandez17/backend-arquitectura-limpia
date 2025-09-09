package utn.models.criterios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import utn.models.domain.Hecho;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
@DiscriminatorValue("FECHA")
public class CriterioPorFecha extends ICriterioDePertenencia {
  private  LocalDate desde;
  private  LocalDate hasta;

  public CriterioPorFecha(LocalDate desde, LocalDate hasta) {
    this.desde = desde;
    this.hasta = hasta;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return !hecho.getFecha().isBefore(desde) && !hecho.getFecha().isAfter(hasta);
  }
}