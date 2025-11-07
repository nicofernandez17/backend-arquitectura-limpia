package utn.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class CsvStorageService {

    private static final String DIRECTORIO_DESTINO = "data/uploads";

    public void guardarArchivo(MultipartFile archivo) throws IOException {
        Path destino = Paths.get(DIRECTORIO_DESTINO);

        // Crear directorio si no existe
        if (!Files.exists(destino)) {
            Files.createDirectories(destino);
        }

        // Guardar el archivo con su nombre original
        Path rutaArchivo = destino.resolve(archivo.getOriginalFilename());
        Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
    }
}
