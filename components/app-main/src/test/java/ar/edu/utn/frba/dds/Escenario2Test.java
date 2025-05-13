package ar.edu.utn.frba.dds;

import deprecated.criterios.CriterioPorCategoria;
import deprecated.criterios.CriterioDePertenencia;
import utn.models.helpers.Categoria;
import deprecated.Coleccion;
import utn.models.domain.Hecho;
import deprecated.fuentes.FuenteDatosEstatica;
import deprecated.ColeccionBuilder;
import deprecated.lectores.AdapterLectorCsv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Escenario2Test {

    private ColeccionBuilder builder;
    private FuenteDatosEstatica fuente;
    private CriterioDePertenencia criterio;

    @BeforeEach
    public void init() {
        // Configurar el builder, la fuente de datos y el criterio de pertenencia
        builder = new ColeccionBuilder();
        fuente = new FuenteDatosEstatica(
            "src/test/resources/desastres_naturales_argentina.csv", new AdapterLectorCsv());
        criterio = new CriterioPorCategoria(new Categoria("Inundación repentina"));
    }

    @Test
    public void testCrearColeccionDesdeCSV() {
        // Configurar datos de prueba
        String titulo = "Colección de Inundaciones";
        String descripcion = "Eventos relacionados con inundaciones repentinas en Argentina";
        List<CriterioDePertenencia> criterios = List.of(criterio);

        // Crear colección con el builder
        Coleccion coleccion = builder
            .iniciarCon(titulo, descripcion, fuente, criterios)
            .buildHechos()
            .build();

        // Verificar que la colección se creó correctamente
        assertEquals(titulo, coleccion.getTitulo());
        assertEquals(descripcion, coleccion.getDescripcion());
        assertEquals(criterios, coleccion.getCriteriosDePertenencia());
        assertNotNull(coleccion.getHechos());
        assertFalse(coleccion.getHechos().isEmpty());
    }

    @Test
    void testFiltrarHechosPorCategoria() {
        // Ruta relativa del archivo CSV
        String rutaArchivo = Paths.get("src", "test", "resources",
            "desastres_naturales_argentina.csv").toString();

        // Instanciar el adaptador y leer el archivo
        AdapterLectorCsv adapter = new AdapterLectorCsv();
        List<Hecho> hechos = adapter.leer(rutaArchivo);

        // Filtrar hechos por categoría "Inundación repentina"
        String categoriaBuscada = "Inundación repentina";
        List<Hecho> hechosFiltrados = hechos.stream()
            .filter(hecho -> categoriaBuscada.equals(hecho.getCategoria().getNombre()))
            .toList();

        // Verificar que los hechos filtrados no sean nulos y contengan datos
        assertNotNull(hechosFiltrados);
        assertFalse(hechosFiltrados.isEmpty());

        // Verificar que todos los hechos filtrados pertenezcan a la categoría buscada
        hechosFiltrados.forEach(hecho -> assertEquals(categoriaBuscada,
            hecho.getCategoria().getNombre()));
    }

    @Test
    void testCantidadTotalDeHechosEnArchivo() {
        // Ruta relativa del archivo CSV
        String rutaArchivo = Paths.get("src", "test",
            "resources", "desastres_naturales_argentina.csv").toString();

        // Instanciar el adaptador y leer el archivo
        AdapterLectorCsv adapter = new AdapterLectorCsv();
        List<Hecho> hechos = adapter.leer(rutaArchivo);

        // Verificar que la cantidad total de hechos sea la esperada
        assertNotNull(hechos);
        assertEquals(14877, hechos.size());
    }
}