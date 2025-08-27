package utn.model.lectores;

import utn.model.domain.Hecho;


import java.util.List;

public interface Lector {

  List<Hecho> leer(String ruta);

}
