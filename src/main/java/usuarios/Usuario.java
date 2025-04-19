package usuarios;

import domain.Hecho;
import domain.SolicitudEliminacion;

public abstract class Usuario {
  private String nombre;
  private String apellido;
  private Integer edad;

  public Usuario(String nombre, String apellido, Integer edad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
  }

  public void aportarHecho(Hecho hecho) {
    //TODO
  }

}