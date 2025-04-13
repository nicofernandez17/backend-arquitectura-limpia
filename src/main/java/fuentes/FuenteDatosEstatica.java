package fuentes;

import domain.Hecho;
import java.util.ArrayList;
import java.util.List;
import servicios.LectorCSV;

public class FuenteDatosEstatica implements FuenteDatos {
  private String rutaCSV;
  private LectorCSV lectorCSV;

  public FuenteDatosEstatica(String rutaCSV, LectorCSV lectorCSV) {
    this.rutaCSV = rutaCSV;
    this.lectorCSV = lectorCSV;
  }

  @Override
  public List<Hecho> obtenerHechos() {
    return lectorCSV.leer(rutaCSV);
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