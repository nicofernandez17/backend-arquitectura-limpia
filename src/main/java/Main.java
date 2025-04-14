import lectores.Lector;
import lectores.LectorCSV;

public class Main {

    public static void main(String[] args) {

        Lector lector = new LectorCSV();

        lector.leer("desastres_naturales_argentina.csv").forEach(x -> System.out.println(x.getTitulo()));



    }


}
