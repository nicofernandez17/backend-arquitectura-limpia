package utn.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hecho")
public class Hecho {

    @Id
    private String id;

    @Column(name = "titulo")
    private String titulo;

    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    @OneToOne
    private Ubicacion ubicacion;
    private LocalDateTime fecha;
    private LocalDateTime fechaDeCarga;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;


}
