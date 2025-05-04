package models.lectores;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import models.domain.Hecho;
import models.helpers.Categoria;
import models.helpers.Origen;
import models.helpers.Ubicacion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdapterLectorCsv implements Lector {

  private CSVReader reader;

  public AdapterLectorCsv() {
  }

  @Override
  public List<Hecho> leer(String rutaArchivo) {
    List<Hecho> hechos = new ArrayList<>();

    try (InputStreamReader inputStreamReader =
             new InputStreamReader(new FileInputStream(rutaArchivo), StandardCharsets.UTF_8)) {
      this.reader = new CSVReader(inputStreamReader);

      String[] linea;
      boolean primeraLinea = true;

      while ((linea = reader.readNext()) != null) {
        if (primeraLinea) {
          primeraLinea = false;
          continue;
        }

        Hecho nuevoHecho = crearHechoDesdeLinea(linea);

        // Verificamos si ya hay un Hecho con ese título
        int indexExistente = buscarHechoPorTitulo(hechos, nuevoHecho.getTitulo());

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

  private Hecho crearHechoDesdeLinea(String[] linea) {
    String titulo = linea[0];
    String descripcion = linea[1];
    String categoriaNombre = linea[2];
    double longitud = Double.parseDouble(linea[3]);
    double latitud = Double.parseDouble(linea[4]);
    String fechaStr = linea[5];

    // Convertir la fecha desde el formato del CSV
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fecha = LocalDate.parse(fechaStr, formatter);

    Ubicacion ubicacion = new Ubicacion(latitud, longitud);
    Categoria categoria = new Categoria(categoriaNombre);

    return new Hecho(
        titulo,
        descripcion,
        categoria,
        ubicacion,
        fecha, // Usar la fecha del CSV
        LocalDate.now(),
        Origen.DATASET
    );
  }

  private int buscarHechoPorTitulo(List<Hecho> hechos, String titulo) {
    for (int i = 0; i < hechos.size(); i++) {
      if (hechos.get(i).getTitulo().equalsIgnoreCase(titulo)) {
        return i;
      }
    }
    return -1;
  }
}