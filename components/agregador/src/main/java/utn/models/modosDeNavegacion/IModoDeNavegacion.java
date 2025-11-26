package utn.models.modosDeNavegacion;

import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;

import java.util.List;
import java.util.Set;

public interface IModoDeNavegacion {
    List<Hecho> obtenerHechos(Coleccion coleccion);
}
