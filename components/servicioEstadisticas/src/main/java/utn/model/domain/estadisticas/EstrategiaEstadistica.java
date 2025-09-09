package utn.model.domain.estadisticas;


import utn.model.domain.Coleccion;

public interface EstrategiaEstadistica {
    void calcular(Coleccion coleccion, EstadisticasColeccion resultado);
}
