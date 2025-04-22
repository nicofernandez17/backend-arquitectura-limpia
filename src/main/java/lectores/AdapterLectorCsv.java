package lectores;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import domain.Hecho;
import helpers.Categoria;
import helpers.Origen;
import helpers.Ubicacion;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdapterLectorCsv implements Lector {

  private CSVReader reader;

  public AdapterLectorCsv() {

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
        Hecho nuevoHecho = new Hecho(
            titulo,
            descripcion,
            categoria,
            ubicacion,
            LocalDate.now(),
            LocalDate.now(),
            Origen.DATASET
        );

        // Verificamos si ya hay un Hecho con ese título
        int indexExistente = -1;
        for (int i = 0; i < hechos.size(); i++) {
          if (hechos.get(i).getTitulo().equalsIgnoreCase(titulo)) {
            indexExistente = i;
            break;
          }
        }

        if (indexExistente != -1) {
          hechos.set(indexExistente, nuevoHecho); // reemplaza el hecho existente
        } else {
          hechos.add(nuevoHecho); // lo agrega si no existía
        }
      }

    } catch (IOException | CsvValidationException e) {
      e.printStackTrace();
    }

    return hechos;
  }
}
