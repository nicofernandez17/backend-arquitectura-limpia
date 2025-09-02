package utn.model.domain;

public class Ubicacion {

    private final Double latitud;

    private final Double longitud;

    public Ubicacion(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String clave() {
        return latitud + "," + longitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public Double getLatitud() {
        return latitud;
    }
}
