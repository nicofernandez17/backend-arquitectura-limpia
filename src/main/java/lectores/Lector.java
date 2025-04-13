package lectores;

import domain.Hecho;

import java.util.List;

public interface Lector {

    public List<Hecho> leer(String ruta);

}
