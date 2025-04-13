package fuentes;

import domain.Hecho;
import lectores.Lector;

import java.util.List;

public class FuenteEstatica extends Fuente {

    private String dataset;
    private Lector lector;

    public List<Hecho> obtenerHechos(String ruta) {

        return List.of();

    }

}
