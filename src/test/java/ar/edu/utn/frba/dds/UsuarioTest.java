package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import domain.Hecho;
import domain.SolicitudEliminacion;
import helpers.Origen;
import org.junit.jupiter.api.Test;
import usuarios.Rol;
import usuarios.Usuario;

class UsuarioTest {

  @Test
  void solicitarEliminacionConHechoComplejoDelegaEnRol() {
    Rol rolMock = mock(Rol.class);
    Usuario usuario = new Usuario("Juan", "Pérez", 30, rolMock);
    Hecho hecho = new Hecho(
        "Inundación y desborde de río en Concordia, Entre Ríos",
        "La región de Concordia en Entre Ríos sufrió una severa inundación debido al desborde del río Uruguay. El incidente dejó a cientos de personas sin hogar, destruyó infraestructuras clave y afectó gravemente la economía local. Las autoridades han declarado estado de emergencia y están coordinando esfuerzos de rescate y reconstrucción.",
        null, null, null, null, Origen.CARGA_MANUAL
    );
    String motivo = "Motivo de eliminación";
    SolicitudEliminacion solicitudMock = new SolicitudEliminacion(hecho, motivo);

    when(rolMock.solicitarEliminacion(hecho, motivo)).thenReturn(solicitudMock);

    SolicitudEliminacion solicitud = usuario.solicitarEliminacion(hecho, motivo);

    assertEquals(solicitudMock, solicitud);
    verify(rolMock).solicitarEliminacion(hecho, motivo);
  }
}
