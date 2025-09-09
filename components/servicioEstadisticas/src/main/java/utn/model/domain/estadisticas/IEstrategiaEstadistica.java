package utn.model.domain.estadisticas;


import utn.model.domain.Coleccion;

public interface IEstrategiaEstadistica {
    void calcular(Coleccion coleccion, EstadisticasColeccion resultado);
}
