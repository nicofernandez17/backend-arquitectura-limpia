import domain.Hecho;
import lectores.Lector;
import lectores.AdapterLectorCSV;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Lector lector = new AdapterLectorCSV();

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
