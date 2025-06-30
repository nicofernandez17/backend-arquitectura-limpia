package utn.services;

import org.springframework.stereotype.Service;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.domain.Hecho;
import utn.models.helpers.ConsensoNivel;
import utn.repositories.HechoRepository;

import java.util.List;

@Service
public class ConsensoService {

    private final HechoRepository hechoRepository;
    private final List<IAlgoritmoConsenso> algoritmos;

    public ConsensoService(HechoRepository hechoRepository,
                           List<IAlgoritmoConsenso> algoritmos) {
        this.hechoRepository = hechoRepository;
        this.algoritmos = algoritmos;
    }

    public void ejecutarConsenso() {
        List<Hecho> hechos = hechoRepository.findAll();

        for (Hecho hecho : hechos) {
            ConsensoNivel mayorNivel = ConsensoNivel.NINGUNO;

            for (IAlgoritmoConsenso algoritmo : algoritmos) {
                ConsensoNivel resultado = algoritmo.aplicar(hecho);
                mayorNivel = ConsensoNivel.max(mayorNivel, resultado);
            }

            hecho.setConsensoNivel(mayorNivel);
        }

        hechoRepository.saveAll(hechos);
    }
}
