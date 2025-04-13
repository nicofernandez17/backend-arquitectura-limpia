package criterios;

import domain.Hecho;

import java.util.Date;

public class CriterioPorFecha implements CriterioDePertenencia {

    private Date desde;
    private Date hasta;

    @Override
    public boolean cumpleCriterio(Hecho hecho) {
        return false;
    }

}
