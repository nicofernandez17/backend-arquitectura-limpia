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

    /**
     * Guarda un archivo CSV en el almacenamiento local y devuelve su ruta absoluta como String.
     *
     * @param archivo El archivo a guardar
     * @return La ruta absoluta del archivo almacenado
     * @throws IOException Si ocurre un error al escribir el archivo
     */
    public String guardarArchivo(MultipartFile archivo) throws IOException {
        Path destino = Paths.get(DIRECTORIO_DESTINO);

        // Crear el directorio si no existe
        if (!Files.exists(destino)) {
            Files.createDirectories(destino);
        }

        // Definir la ruta del archivo final
        Path rutaArchivo = destino.resolve(archivo.getOriginalFilename());

        // Copiar el contenido al destino (reemplazando si ya existe)
        Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);

        // Devolver la ruta absoluta como String
        return rutaArchivo.toAbsolutePath().toString();
    }
}

