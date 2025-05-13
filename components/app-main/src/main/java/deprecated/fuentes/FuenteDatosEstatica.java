package deprecated.fuentes;

import utn.models.domain.Hecho;
import java.util.ArrayList;
import java.util.List;
import deprecated.lectores.Lector;

public class FuenteDatosEstatica implements FuenteDatos {
  private final String ruta;
  private final Lector lector;

  public FuenteDatosEstatica(String ruta, Lector lector) {
    this.ruta = ruta;
    this.lector = lector;
  }

  @Override
  public List<Hecho> obtenerHechos() {
    return lector.leer(ruta);
  }


  // Otros métodos futuros:
  public List<Hecho> extraerDatosdesdesql() {
    // no requerido aún
    return new ArrayList<>();
  }

  public List<Hecho> extraerDatosdesdeword() {
    // no requerido aún
    return new ArrayList<>();
  }
}