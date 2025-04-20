package usuarios;

import domain.Coleccion;
import domain.Hecho;

public abstract class Usuario {
  private String nombre;
  private String apellido;
  private Integer edad;

  public Usuario(String nombre, String apellido, Integer edad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
  }


}