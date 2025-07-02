package utn.models.algoritmos.impl;

import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.domain.Hecho;
import utn.models.helpers.ConsensoNivel;

import java.util.List;

public class AlgoritmoMayoriaSimple implements IAlgoritmoConsenso {

    @Override
    public ConsensoNivel aplicar(Hecho hecho, int totalFuentes) {
        if (totalFuentes <= 0) return ConsensoNivel.NINGUNO;
        int cantidad = hecho.getFuentes().size();

        if (cantidad >= Math.ceil(totalFuentes / 2.0)) {
            return ConsensoNivel.MAYORIA_SIMPLE;
        }
        return ConsensoNivel.NINGUNO;
    }

    @Override
    public ConsensoNivel getNivelQueAplica() {
        return ConsensoNivel.MAYORIA_SIMPLE;
    }
}
