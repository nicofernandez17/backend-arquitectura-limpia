package criterios;

import domain.Hecho;

import java.time.LocalDate;
import java.util.Date;

public class CriterioPorFecha implements CriterioDePertenencia {
    private LocalDate desde;
    private LocalDate hasta;

    public void CriterioPorRangoDeFecha(LocalDate desde, LocalDate hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    public boolean cumple(Hecho hecho) {
        LocalDate fecha = hecho.getFecha();
        return (fecha.isEqual(desde) || fecha.isAfter(desde)) &&
            (fecha.isEqual(hasta) || fecha.isBefore(hasta));
    }
}