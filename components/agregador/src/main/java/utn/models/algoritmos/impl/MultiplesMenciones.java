package utn.models.algoritmos.impl;

import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.domain.Hecho;

import java.util.List;

public class MultiplesMenciones implements IAlgoritmoConsenso {
    @Override
    public boolean esConsensuado(Hecho hecho) {
        return false;
    }

    @Override
    public List<Hecho> filtrarHechosConsensuados(List<Hecho> hechos) {
        return List.of();
    }
}
