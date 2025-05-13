import utn.models.domain.Hecho;
import deprecated.lectores.Lector;
import deprecated.lectores.AdapterLectorCsv;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Lector lector = new AdapterLectorCsv();

        List<Hecho> hechos = lector.leer("src/test/resources/desastres_naturales_argentina.csv");

        for (Hecho hecho : hechos) {
            System.out.println("Título: " + hecho.getTitulo());
            System.out.println("Descripción: " + hecho.getDescripcion());
            System.out.println("Ubicación: (" + hecho.getUbicacion().getLatitud() + ", " + hecho.getUbicacion().getLongitud() + ")");
            System.out.println("Categoría: " + hecho.getCategoria().getNombre());
            System.out.println("Fecha: " + hecho.getFecha());
            System.out.println("-------------------------------------");
        }

    }


}
