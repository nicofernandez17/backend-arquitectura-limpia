package criterios;

import domain.Hecho;
import helpers.Ubicacion;

public class CriterioPorUbicacion implements CriterioDePertenencia {

    private Ubicacion ubicacion;

    @Override
    public boolean cumpleCriterio(Hecho hecho) {
        return false;
    }

}
