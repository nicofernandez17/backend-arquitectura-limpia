package utn.services.fuentes;

import utn.models.domain.Hecho;

import java.util.List;
import java.util.Optional;

public interface IFuenteService {
    List<Hecho> obtenerHechos();
}
