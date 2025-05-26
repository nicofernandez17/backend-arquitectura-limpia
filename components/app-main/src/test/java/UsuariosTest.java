import org.junit.jupiter.api.Test;
import utn.models.usuarios.Administrador;
import utn.models.usuarios.Contribuyente;
import utn.models.domain.Hecho;
import utn.models.domain.SolicitudEliminacion;
import deprecated.Coleccion;
import deprecated.ColeccionBuilder;
import deprecated.fuentes.FuenteDatos;
import deprecated.criterios.CriterioDePertenencia;

import java.util.List;

import static org.mockito.Mockito.*;

class UsuariosTest {

    @Test
    void aportarHechoLlamaAgregarHechoEnColeccion() {
        Contribuyente c = new Contribuyente("Juan", "Perez", 30);
        Coleccion coleccion = mock(Coleccion.class);
        Hecho hecho = mock(Hecho.class);
        c.aportarHecho(coleccion, hecho);
        verify(coleccion).agregarHecho(hecho);
    }

    @Test
    void crearColeccionUsaBuilderCorrectamente() {
        Administrador admin = new Administrador();
        ColeccionBuilder builder = mock(ColeccionBuilder.class);
        Coleccion coleccion = mock(Coleccion.class);
        FuenteDatos fuente = mock(FuenteDatos.class);
        List<CriterioDePertenencia> criterios = mock(List.class);

        when(builder.iniciarCon(anyString(), anyString(), any(), any())).thenReturn(builder);
        when(builder.buildCriterios(any())).thenReturn(builder);
        when(builder.buildHechos()).thenReturn(builder);
        when(builder.build()).thenReturn(coleccion);

        admin.setBuilder(builder);
        Coleccion resultado = admin.crearColeccion("titulo", "desc", fuente, criterios);

        verify(builder).iniciarCon("titulo", "desc", fuente, criterios);
        verify(builder).buildCriterios(criterios);
        verify(builder).buildHechos();
        verify(builder).build();
    }

    @Test
    void aceptarSolicitudLlamaAceptar() {
        Administrador admin = new Administrador();
        SolicitudEliminacion solicitud = mock(SolicitudEliminacion.class);
        admin.aceptarSolicitud(solicitud);
        verify(solicitud).aceptar();
    }

    @Test
    void rechazarSolicitudLlamaRechazar() {
        Administrador admin = new Administrador();
        SolicitudEliminacion solicitud = mock(SolicitudEliminacion.class);
        admin.rechazarSolicitud(solicitud);
        verify(solicitud).rechazar();
    }
}