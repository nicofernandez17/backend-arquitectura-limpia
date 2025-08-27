package utn.models.modosDeNavegacion.impl;

import org.springframework.stereotype.Component;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.modosDeNavegacion.IModoDeNavegacion;

import java.util.List;

@Component("curado")
public class NavegacionCurada implements IModoDeNavegacion {
    @Override
    public List<Hecho> obtenerHechos(Coleccion coleccion) {
        return coleccion.getHechosFiltradosPorConsenso();
    }
}
