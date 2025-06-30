package utn.models.helpers;

public enum ConsensoNivel {
    NINGUNO(0),
    NIVEL_1(1),
    NIVEL_2(2),
    NIVEL_3(3);

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