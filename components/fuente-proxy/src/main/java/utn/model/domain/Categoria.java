package utn.model.domain;

public class Categoria {

    private final String nombre;

    public Categoria(String categoriaNombre) {
        this.nombre = categoriaNombre;
    }

    public String getNombre() {
        return nombre;
    }
}

