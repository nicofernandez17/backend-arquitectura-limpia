import domain.Hecho;
import lectores.Lector;
import lectores.LectorCSV;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Lector lector = new LectorCSV();

        //lector.leer("desastres_naturales_argentina.csv").forEach(x -> System.out.println(x.getTitulo()));

        Set<String> titulosVistos = new HashSet<>();
        List<Hecho> hechos = lector.leer("desastres_naturales_argentina.csv");
        List<Hecho> hechosSinDuplicados = hechos.stream()
                .filter(hecho -> titulosVistos.add(hecho.getTitulo()))
                .collect(Collectors.toList());
        hechosSinDuplicados.forEach(h -> System.out.println(h.getTitulo()));
    }


}
