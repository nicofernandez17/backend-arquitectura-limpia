package usuarios;

import domain.Coleccion;
import domain.Hecho;
import Solicitudes.SolicitudEliminacion;

public class Contribuyente extends Usuario {

  public Contribuyente(String nombre, String apellido, Integer edad, Usuario rolMock) {
    super(nombre, apellido, edad);
  }

  public SolicitudEliminacion solicitarEliminacion(Hecho hecho, String motivo) {
    return new SolicitudEliminacion(hecho, motivo);
    //Eventualmente lo guardará en una base de datos dinámica
  }

  public void aportarHecho(Coleccion coleccion, Hecho hecho) {
    coleccion.agregarHecho(hecho);
  }
}