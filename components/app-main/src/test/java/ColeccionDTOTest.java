import org.junit.jupiter.api.Test;
import utn.models.dtos.ColeccionDTO;
import utn.models.dtos.HechoDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColeccionDtoTest {

    @Test
    void crearColeccionDTOConBuilder() {
        HechoDTO hecho = HechoDTO.builder()
                .titulo("t1")
                .descripcion("desc")
                .categoria("cat")
                .latitud(1.0)
                .longitud(2.0)
                .fecha_hecho("2024-01-01")
                .build();

        ColeccionDTO coleccion = ColeccionDTO.builder()
                .id("id1")
                .titulo("titulo1")
                .hechos(List.of(hecho))
                .build();

        assertEquals("id1", coleccion.getId());
        assertEquals("titulo1", coleccion.getTitulo());
        assertEquals(1, coleccion.getHechos().size());
        assertEquals("t1", coleccion.getHechos().get(0).getTitulo());
    }

    @Test
    void settersYGettersFuncionanCorrectamente() {
        ColeccionDTO coleccion = ColeccionDTO.builder().build();
        coleccion.setId("id2");
        coleccion.setTitulo("titulo2");
        assertEquals("id2", coleccion.getId());
        assertEquals("titulo2", coleccion.getTitulo());
    }

    @Test
    void crearHechoDTOConBuilder() {
        HechoDTO hecho = HechoDTO.builder()
                .titulo("t2")
                .descripcion("desc2")
                .categoria("cat2")
                .latitud(3.0)
                .longitud(4.0)
                .fecha_hecho("2024-02-02")
                .build();

        assertEquals("t2", hecho.getTitulo());
        assertEquals("desc2", hecho.getDescripcion());
        assertEquals("cat2", hecho.getCategoria());
        assertEquals(3.0, hecho.getLatitud());
        assertEquals(4.0, hecho.getLongitud());
        assertEquals("2024-02-02", hecho.getFecha_hecho());
    }
}