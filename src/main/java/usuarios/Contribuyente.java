package usuarios;

import domain.Hecho;
import Solicitudes.SolicitudEliminacion;

public class Contribuyente extends Usuario {

  public Contribuyente(String nombre, String apellido, Integer edad, Usuario rolMock) {
    //TODO
    //Actualizar a DatosUsuario
    super(nombre, apellido, edad);
  }

  public void solicitarEliminacion(Hecho hecho, String motivo) {
    var solicitud = new SolicitudEliminacion(hecho, motivo);
    solicitud.agregar();
  }
}