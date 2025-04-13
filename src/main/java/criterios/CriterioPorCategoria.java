package criterios;

import domain.Hecho;
import helpers.Categoria;

public class CriterioPorCategoria implements CriterioDePertenencia {

    private Categoria categoria;


    @Override
    public boolean cumpleCriterio(Hecho hecho) {
        return false;
    }
}
