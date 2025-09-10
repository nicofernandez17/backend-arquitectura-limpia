package utn.models.algoritmos.impl;

import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.domain.Hecho;
import utn.models.helpers.ConsensoNivel;

public class AlgoritmoMultiplesMenciones implements IAlgoritmoConsenso {

    @Override
    public ConsensoNivel aplicar(Hecho hecho, int totalFuentes) {
        if (hecho.getFuentes().size() >= 2) {
            return ConsensoNivel.MULTIPLES_MENCIONES;
        }
        return ConsensoNivel.NINGUNO;
    }

    @Override
    public ConsensoNivel getNivelQueAplica() {
        return ConsensoNivel.MULTIPLES_MENCIONES;
    }
}
