package utn.models.helpers;

import utn.models.domain.Hecho;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HechoClaveUtils {

    public static String generarClaveLogica(Hecho h) {
        return Stream.of(
                normalizar(h.getTitulo()),
                normalizar(h.getDescripcion()),
                //normalizar(h.getCategoria() != null ? h.getCategoria().toString() : ""),
                normalizar(h.getUbicacion() != null ? h.getUbicacion().clave() : ""),
                h.getFecha() != null ? h.getFecha().toString() : ""
        ).collect(Collectors.joining("|"));
    }

    private static String normalizar(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }
}