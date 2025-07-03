package utn.models.modosDeNavegacion;

import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;

import java.util.List;

public interface IModoDeNavegacion {
    List<Hecho> obtenerHechos(Coleccion coleccion);
}
