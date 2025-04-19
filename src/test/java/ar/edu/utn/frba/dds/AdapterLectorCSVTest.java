package ar.edu.utn.frba.dds;

import domain.Hecho;
import lectores.AdapterLectorCSV;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AdapterLectorCSVTest {

    @Test
    void testLeerArchivoDesastresNaturales() {
        // Ruta relativa del archivo CSV
        String rutaArchivo = Paths.get("src", "test", "resources", "desastres_naturales_argentina.csv").toString();

        // Instanciar el adaptador y leer el archivo
        AdapterLectorCSV adapter = new AdapterLectorCSV();
        List<Hecho> hechos = adapter.leer(rutaArchivo);

        // Verificar que la lista no sea nula y contenga datos
        assertNotNull(hechos);
        assertEquals(15000, hechos.size());

        // Verificar algunos datos específicos
        Hecho primerHecho = hechos.get(0);
        assertNotNull(primerHecho.getTitulo());
        assertNotNull(primerHecho.getDescripcion());
    }
}