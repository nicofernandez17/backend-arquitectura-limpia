package utn.model.domain;

import jakarta.persistence.Embeddable;
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

    private String nombre;
    @Id
    private Long id;

    public Categoria(String categoriaNombre) {
        this.nombre = categoriaNombre;
    }




}

