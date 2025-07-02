package utn.models.helpers;

public enum ConsensoNivel {
    NINGUNO(0),
    MULTIPLES_MENCIONES(1),
    MAYORIA_SIMPLE(2),
    MAYORIA_ABSOLUTA(3);

    private final int prioridad;

    ConsensoNivel(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public static ConsensoNivel max(ConsensoNivel a, ConsensoNivel b) {
        return a.getPrioridad() >= b.getPrioridad() ? a : b;
    }
}