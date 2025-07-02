package utn.models.algoritmos.impl;

import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.domain.Hecho;
import utn.models.helpers.ConsensoNivel;

import java.util.List;

public class AlgoritmoMayoriaAbsoluta implements IAlgoritmoConsenso {

    @Override
    public ConsensoNivel aplicar(Hecho hecho, int totalFuentes) {
        if (totalFuentes <= 0) return ConsensoNivel.NINGUNO;
        return hecho.getFuentes().size() == totalFuentes
                ? ConsensoNivel.MAYORIA_ABSOLUTA
                : ConsensoNivel.NINGUNO;
    }

    @Override
    public ConsensoNivel getNivelQueAplica() {
        return ConsensoNivel.MAYORIA_ABSOLUTA;
    }
}
