package utn.models.modosDeNavegacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class ModoDeNavegacionFactory {

    private final Map<String, IModoDeNavegacion> estrategias;

    @Autowired
    public ModoDeNavegacionFactory(Map<String, IModoDeNavegacion> estrategias) {
        this.estrategias = estrategias;
    }

    public IModoDeNavegacion obtener(String modo) {
        return Optional.ofNullable(estrategias.get(modo.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException("Modo de navegación inválido: " + modo));
    }
}