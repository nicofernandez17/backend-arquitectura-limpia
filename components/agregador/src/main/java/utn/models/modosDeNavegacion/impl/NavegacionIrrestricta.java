package utn.models.modosDeNavegacion.impl;

import org.springframework.stereotype.Component;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.modosDeNavegacion.IModoDeNavegacion;

import java.util.List;

@Component("irrestricto")
public class NavegacionIrrestricta implements IModoDeNavegacion {
    @Override
    public List<Hecho> obtenerHechos(Coleccion coleccion) {
        return coleccion.getHechos();
    }
}
