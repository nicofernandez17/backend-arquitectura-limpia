package utn.models.helpers;

import utn.models.domain.Hecho;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HechoClaveUtils {

    // Genera un hash SHA-256 a partir de los campos del hecho
    public static String generarClaveHash(Hecho h) {
        String base = Stream.of(
                normalizar(h.getTitulo()),
                normalizar(h.getDescripcion()),
                h.getFecha() != null ? h.getFecha().toString() : ""
        ).collect(Collectors.joining("|"));

        return sha256(base);
    }

    // Genera la clave lógica sin hash (solo concatenación de campos)
    public static String generarClaveLogica(Hecho h) {
        return Stream.of(
                normalizar(h.getTitulo()),
                normalizar(h.getDescripcion()),
                h.getFecha() != null ? h.getFecha().toString() : ""
        ).collect(Collectors.joining("|"));
    }

    private static String normalizar(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    private static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convertir a hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 no soportado", e);
        }
    }
}

