package utn.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ubicacion")
public class Ubicacion {

    @Id
    private Long id;

    @Column(nullable = false, name = "latitud")
    private Double latitud;

    @Column(nullable = false, name = "longitud")
    private Double longitud;

    public Ubicacion(Double latitud, Double longitud) {this.latitud = latitud; this.longitud = longitud;}

    public String clave() {
        return latitud + "," + longitud;
    }


}
