import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utn.schedulers.FuenteScheduler;
import utn.services.ColeccionService;

class FuenteSchedulerTest {

    @Test
    void testActualizarColeccionesLlamaAlServicio() {
        ColeccionService coleccionServiceMock = Mockito.mock(ColeccionService.class);
        FuenteScheduler scheduler = new FuenteScheduler(coleccionServiceMock);

        scheduler.actualizarColecciones();

        Mockito.verify(coleccionServiceMock, Mockito.times(1)).actualizarHechosDeTodasLasColecciones();
    }
}