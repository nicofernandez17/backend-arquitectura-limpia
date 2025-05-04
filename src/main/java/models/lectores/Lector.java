package models.lectores;

import models.domain.Hecho;
import java.util.List;

public interface Lector {

  List<Hecho> leer(String ruta);

}
