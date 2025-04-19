package usuarios;

import domain.Hecho;
import domain.SolicitudEliminacion;

public class Contribuyente extends Usuario {

  public Contribuyente(String nombre, String apellido, Integer edad) {
    super(nombre, apellido, edad);
  }

  public SolicitudEliminacion solicitarEliminacion(Hecho hecho, String motivo) {
    return new SolicitudEliminacion(hecho, motivo);
  }
}