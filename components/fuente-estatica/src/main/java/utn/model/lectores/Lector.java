package utn.model.lectores;

import utn.model.dto.HechoDTO;


import java.util.List;

public interface Lector {

  List<HechoDTO> leer(String ruta);

}
