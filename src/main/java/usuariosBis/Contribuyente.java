package usuariosBis;

import domain.Coleccion;
import domain.Hecho;

public class Contribuyente implements Rol{

    public void aportarHecho(Coleccion coleccion, Hecho hecho, Usuario usuario) {

        coleccion.agregarHecho(hecho);

    }

}
