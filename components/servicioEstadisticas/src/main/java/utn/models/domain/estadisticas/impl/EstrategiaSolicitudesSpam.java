package utn.models.domain.estadisticas.impl;

import org.springframework.stereotype.Service;
import utn.models.domain.Coleccion;
import utn.models.domain.estadisticas.EstadisticasColeccion;
import utn.models.domain.estadisticas.IEstrategiaEstadistica;
import utn.repositories.ISolicitudEliminacionRepository;

@Service
public class EstrategiaSolicitudesSpam implements IEstrategiaEstadistica {

    private final ISolicitudEliminacionRepository solicitudRepo;

    public EstrategiaSolicitudesSpam(ISolicitudEliminacionRepository repo) {
        this.solicitudRepo = repo;
    }

    @Override
    public void calcular(Coleccion coleccion, EstadisticasColeccion resultado) {
        int spam = (int) solicitudRepo.countByEsSpamTrue();
        resultado.setSolicitudesSpam(spam);
    }
}
