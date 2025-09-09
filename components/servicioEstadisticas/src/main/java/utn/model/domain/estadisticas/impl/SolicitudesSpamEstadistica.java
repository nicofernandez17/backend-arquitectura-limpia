package utn.model.domain.estadisticas.impl;

public class SolicitudesSpamEstadistica implements IEstrategiaEstadistica {

    @Override
    public String getNombre() {
        return "Cantidad de solicitudes de eliminación spam";
    }

    @Override
    public Long calcular(Coleccion coleccion) {
        return coleccion.getHechos().stream()
                .filter(Hecho::isEliminado) // suponiendo que marcado como eliminado = spam
                .count();
    }
}