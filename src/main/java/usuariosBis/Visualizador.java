package usuariosBis;

import domain.Coleccion;
import domain.Hecho;

public class Visualizador implements Rol{

    public void aportarHecho(Coleccion coleccion, Hecho hecho, Usuario usuario) {

        coleccion.agregarHecho(hecho);
        usuario.cambiarRol(new Contribuyente());

    }

}
