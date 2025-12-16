package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.helpers.ConsensoNivel;
import utn.repositories.IColeccionRepository;
import utn.repositories.IHechoRepository;

import java.util.List;

@Service
public class ConsensoService {

    private final IColeccionRepository coleccionRepo;
    private final FuenteProvider fuenteConfig;
    private final IHechoRepository hechoRepo;

    @Autowired
    public ConsensoService(IColeccionRepository coleccionRepo,
                           FuenteProvider fuenteConfig, IHechoRepository hechoRepo) {
        this.coleccionRepo = coleccionRepo;
        this.fuenteConfig = fuenteConfig;
        this.hechoRepo = hechoRepo;
    }

    public void aplicarConsensoPorColeccion() {
        int totalFuentes = fuenteConfig.getTodasLasFuentes().size();
        List<Coleccion> colecciones = coleccionRepo.findAll();

        for (Coleccion coleccion : colecciones) {
            IAlgoritmoConsenso algoritmo = coleccion.getAlgoritmo();
            if (algoritmo == null) continue;

            boolean huboCambios = false;

            for (Hecho hecho : coleccion.getHechos()) {
                ConsensoNivel nuevoNivel = algoritmo.aplicar(hecho, totalFuentes);

                if (nuevoNivel == ConsensoNivel.MULTIPLES_MENCIONES) {
                    System.out.println(nuevoNivel);
                    System.out.println(coleccion.getId());
                    System.out.println(hecho.getTitulo());
                }

                if (nuevoNivel != null) {
                    ConsensoNivel nivelFinal =
                            ConsensoNivel.max(hecho.getConsensoNivel(), nuevoNivel);

                    if (nivelFinal != hecho.getConsensoNivel()) {
                        hecho.setConsensoNivel(nivelFinal);
                        hechoRepo.save(hecho);
                        System.out.println("Hecho actualizado: " + nivelFinal);
                    }
                }
            }


        }
    }
}

