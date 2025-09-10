package utn.models.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Ubicacion {


    @Column(nullable = false, name = "latitud")
    private Double latitud;

    @Column(nullable = false, name = "longitud")
    private Double longitud;

    public String clave() {
        return latitud + "," + longitud;
    }


}
