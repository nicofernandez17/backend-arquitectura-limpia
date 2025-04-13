package fuentes;

import domain.Hecho;
import java.util.ArrayList;
import java.util.List;

import lectores.Lector;

public class FuenteDatosEstatica implements FuenteDatos {
  private String ruta;
  private Lector lector;

  public FuenteDatosEstatica(String ruta, Lector lector) {
    this.ruta = ruta;
    this.lector = lector;
  }

  @Override
  public List<Hecho> obtenerHechos() {
    return lector.leer(ruta);
  }




  // Otros métodos futuros:
  public List<Hecho> extraerDatosDesdeSQL() {
    // no requerido aún
    return new ArrayList<>();
  }

  public List<Hecho> extraerDatosDesdeWord() {
    // no requerido aún
    return new ArrayList<>();
  }
}