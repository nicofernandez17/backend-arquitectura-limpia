package utn.models.lectores;

import utn.models.domain.Hecho;


import java.util.List;

public interface Lector {

  List<Hecho> leer(String ruta);

}
