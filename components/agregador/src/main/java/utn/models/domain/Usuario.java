package utn.models.domain;

import jakarta.persistence.*;
import lombok.*;
import utn.models.domain.usuarios.Permiso;
import utn.models.domain.usuarios.Rol;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String nombreDeUsuario;

    @Column(nullable = false)
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "usuario_permisos",
            joinColumns = @JoinColumn(name = "usuario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "permiso")
    private List<Permiso> permisos = new ArrayList<>();

    public Usuario() {}


}

