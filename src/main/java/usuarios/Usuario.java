package usuarios;

import domain.Hecho;
import domain.SolicitudEliminacion;

public class Usuario {
  private String nombre;
  private String apellido;
  private Integer edad;
  private Rol rol;

  public Usuario(String nombre, String apellido, Integer edad, Rol rol) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.rol = rol;
  }

  public SolicitudEliminacion solicitarEliminacion(Hecho hecho, String motivo) {
    return rol.solicitarEliminacion(hecho, motivo);
  }

  public void aportarHecho(Hecho hecho) {
    rol.aportarHecho(hecho);
  }

  // Getters y setters
}