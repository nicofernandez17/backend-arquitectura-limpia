package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.configs.FuenteConfig;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.helpers.ConsensoNivel;
import utn.repositories.ColeccionRepository;
import utn.repositories.HechoRepository;

import java.util.List;

@Service
public class ConsensoService {

    private final ColeccionRepository coleccionRepo;
    private final FuenteConfig fuenteConfig;

    @Autowired
    public ConsensoService(ColeccionRepository coleccionRepo,
                           FuenteConfig fuenteConfig) {
        this.coleccionRepo = coleccionRepo;
        this.fuenteConfig = fuenteConfig;
    }

    public void aplicarConsensoPorColeccion() {
        int totalFuentes = fuenteConfig.getUrls().size();
        List<Coleccion> colecciones = coleccionRepo.findAll();

        for (Coleccion coleccion : colecciones) {
            IAlgoritmoConsenso algoritmo = coleccion.getAlgoritmo(); // suponer que lo tiene configurado
            if (algoritmo == null) continue;

            for (Hecho hecho : coleccion.getHechos()) {
                ConsensoNivel nuevoNivel = algoritmo.aplicar(hecho, totalFuentes);

                // Mantener el valor más alto entre el actual y el nuevo
                if (nuevoNivel != null) {
                    hecho.setConsensoNivel(
                            ConsensoNivel.max(hecho.getConsensoNivel(), nuevoNivel)
                    );
                }
            }

            // Ajustar el consenso mínimo requerido de la colección si es más bajo
            if (coleccion.getConsensoNivel().getPrioridad() < algoritmo.getNivelQueAplica().getPrioridad()) {
                coleccion.setConsensoNivel(algoritmo.getNivelQueAplica());
            }
        }
    }
}
