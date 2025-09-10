package utn.models.domain.estadisticas;


import utn.models.domain.Coleccion;

public interface IEstrategiaEstadistica {
    void calcular(Coleccion coleccion, EstadisticasColeccion resultado);
}
