package utn.models.algoritmos;

import utn.models.domain.Hecho;

import java.util.List;

public interface IAlgoritmoConsenso {
    boolean esConsensuado(Hecho hecho);
    List<Hecho> filtrarHechosConsensuados(List<Hecho> hechos);
}
