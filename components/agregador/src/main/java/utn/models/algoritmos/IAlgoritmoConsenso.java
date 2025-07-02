package utn.models.algoritmos;

import utn.models.domain.Hecho;
import utn.models.helpers.ConsensoNivel;

import java.util.List;

public interface IAlgoritmoConsenso {
    ConsensoNivel aplicar(Hecho hecho, int totalFuentes);
    ConsensoNivel getNivelQueAplica(); // nuevo
}
