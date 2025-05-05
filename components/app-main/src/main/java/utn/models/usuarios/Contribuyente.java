package utn.models.usuarios;

import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;

public class Contribuyente {


    private final String nombre;
    private final String apellido;
    private final Integer edad;

    public Contribuyente(String nombre, String apellido, Integer edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public void aportarHecho(Coleccion coleccion, Hecho hecho) {
        coleccion.agregarHecho(hecho);
    }



}
