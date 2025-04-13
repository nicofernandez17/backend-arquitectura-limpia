package lectores;

import domain.Hecho;

import java.util.List;

public interface Lector {

    public List<Hecho> obtenerDatos(String ruta);

}
