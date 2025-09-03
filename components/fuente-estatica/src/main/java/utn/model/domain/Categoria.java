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
@Table(name = "categoria")
public class Categoria {

    @Id
    private Long id;

    @Column(name = "nombre")
    private  String nombre;

    public Categoria(String categoriaNombre) {
        this.nombre = categoriaNombre;
    }

}

