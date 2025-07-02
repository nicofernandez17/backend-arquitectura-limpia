package utn.services;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Component
public class EstadoAgregacion {
    private static final Path ARCHIVO = Paths.get("ultima-consulta.txt");

    public LocalDateTime obtenerUltimaConsulta() {
        try {
            if (!Files.exists(ARCHIVO)) return LocalDateTime.MIN;
            return LocalDateTime.parse(Files.readString(ARCHIVO));
        } catch (IOException e) {
            e.printStackTrace();
            return LocalDateTime.MIN;
        }
    }

    public void actualizarUltimaConsulta(LocalDateTime nueva) {
        try {
            Files.writeString(ARCHIVO, nueva.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}