package usuarios;

import domain.Hecho;
import Solicitudes.SolicitudAgregado;

public abstract class Usuario {
  private DatosUsuario datosUsuario;

  public Usuario(String nombre, String apellido, Integer edad) {
    this.datosUsuario = new DatosUsuario(nombre, apellido, edad);
  }

  public void aportarHecho(Hecho hecho) {
    var solicitud = new SolicitudAgregado(hecho, datosUsuario);
    solicitud.agregar();

  }

}