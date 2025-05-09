package utn.model.lectores;

import utn.model.HechoDTO;


import java.util.List;

public interface Lector {

  List<HechoDTO> leer(String ruta);

}
