import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utn.schedulers.FuenteScheduler;
import utn.services.AgregadorService;
import utn.services.ColeccionService;

class FuenteSchedulerTest {

    @Test
    void testActualizarColeccionesLlamaAlServicio() {
        ColeccionService coleccionServiceMock = Mockito.mock(ColeccionService.class);
        AgregadorService agregadorServiceMock = Mockito.mock(AgregadorService.class);
        FuenteScheduler scheduler = new FuenteScheduler(coleccionServiceMock,agregadorServiceMock);

        scheduler.actualizarColecciones();

        Mockito.verify(coleccionServiceMock, Mockito.times(1)).actualizarHechosDeTodasLasColecciones();
    }
}