package utn.models.domain;

import java.util.Arrays;

public enum ProvinciaArg {

    BUENOS_AIRES("Buenos Aires"),
    CATAMARCA("Catamarca"),
    CHACO("Chaco"),
    CHUBUT("Chubut"),
    CORDOBA("Córdoba"),
    CORRIENTES("Corrientes"),
    ENTRE_RIOS("Entre Ríos"),
    FORMOSA("Formosa"),
    JUJUY("Jujuy"),
    LA_PAMPA("La Pampa"),
    LA_RIOJA("La Rioja"),
    MENDOZA("Mendoza"),
    MISIONES("Misiones"),
    NEUQUEN("Neuquén"),
    RIO_NEGRO("Río Negro"),
    SALTA("Salta"),
    SAN_JUAN("San Juan"),
    SAN_LUIS("San Luis"),
    SANTA_CRUZ("Santa Cruz"),
    SANTA_FE("Santa Fe"),
    SANTIAGO_DEL_ESTERO("Santiago del Estero"),
    TIERRA_DEL_FUEGO("Tierra del Fuego, Antártida e Islas del Atlántico Sur"),
    TUCUMAN("Tucumán");

    private final String nombre;

    ProvinciaArg(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Busca un enum por nombre, ignorando mayúsculas/minúsculas y tildes.
     */
    public static ProvinciaArg fromNombre(String nombre) {
        return Arrays.stream(values())
                .filter(p -> p.nombre.equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Provincia desconocida: " + nombre));
    }
}