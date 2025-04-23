package usuariosBis;

public class Usuario {

    private final String nombre;
    private final String apellido;
    private final Integer edad;
    public Rol rol;

    public Usuario(String nombre, String apellido, Integer edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public void cambiarRol(Rol rol) {

        this.rol = rol;

    }

    public Rol getRol() {
        return rol;
    }

}
