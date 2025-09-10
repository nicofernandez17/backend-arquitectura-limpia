package utn.models.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Ubicacion {

    private  Double latitud;

    private  Double longitud;

    public String clave() {
        return latitud + "," + longitud;
    }


}
