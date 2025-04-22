package usuarios;

public abstract class Usuario {
  private final String nombre;
  private final String apellido;
  private final Integer edad;

  public Usuario(String nombre, String apellido, Integer edad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
  }


}