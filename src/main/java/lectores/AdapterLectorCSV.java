package lectores;

import com.opencsv.exceptions.CsvValidationException;
import domain.Hecho;
import helpers.Categoria;
import helpers.Origen;
import helpers.Ubicacion;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class AdapterLectorCSV implements Lector {

    private CSVReader reader;

    public AdapterLectorCSV() {

    }

    @Override
    public List<Hecho> leer(String rutaArchivo) {
        List<Hecho> hechos = new ArrayList<>();

        try (FileReader fileReader = new FileReader(rutaArchivo)) {
            this.reader = new CSVReader(fileReader);

            String[] linea;
            boolean primeraLinea = true;

            while ((linea = reader.readNext()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String titulo = linea[0];
                String descripcion = linea[1];
                String categoriaNombre = linea[2];
                double longitud = Double.parseDouble(linea[3]);
                double latitud = Double.parseDouble(linea[4]);
                String fecha = linea[5];

                Ubicacion ubicacion = new Ubicacion(latitud, longitud);
                Categoria categoria = new Categoria(categoriaNombre);
                Hecho hecho = new Hecho(
                        titulo,
                        descripcion,
                        categoria,
                        ubicacion,
                        LocalDate.now(),
                        LocalDate.now(),
                        Origen.DATASET
                );
                hechos.add(hecho);
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return hechos;
    }
}
