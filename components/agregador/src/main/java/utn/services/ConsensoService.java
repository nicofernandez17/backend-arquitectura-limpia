package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.helpers.ConsensoNivel;
import utn.repositories.IColeccionRepository;

import java.util.List;

@Service
public class ConsensoService {

    private final IColeccionRepository coleccionRepo;
    private final FuenteProvider fuenteConfig;

    @Autowired
    public ConsensoService(IColeccionRepository coleccionRepo,
                           FuenteProvider fuenteConfig) {
        this.coleccionRepo = coleccionRepo;
        this.fuenteConfig = fuenteConfig;
    }

    public void aplicarConsensoPorColeccion() {
        int totalFuentes = fuenteConfig.getTodasLasFuentes().size();
        List<Coleccion> colecciones = coleccionRepo.findAll();

        for (Coleccion coleccion : colecciones) {
            IAlgoritmoConsenso algoritmo = coleccion.getAlgoritmo();
            if (algoritmo == null) continue;

            for (Hecho hecho : coleccion.getHechos()) {
                ConsensoNivel nuevoNivel = algoritmo.aplicar(hecho, totalFuentes);
                if (nuevoNivel == ConsensoNivel.MULTIPLES_MENCIONES) {
                    System.out.println(nuevoNivel);
                    System.out.println(coleccion.getId());
                    System.out.println(hecho.getTitulo());
                }
                if (nuevoNivel != null) {
                    hecho.setConsensoNivel(
                            ConsensoNivel.max(hecho.getConsensoNivel(), nuevoNivel)
                    );
                }
            }


        }
    }
}
