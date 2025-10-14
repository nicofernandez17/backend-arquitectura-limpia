package utn.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;
    private String nombre;
    private String nombreDeUsuario;
    private String contrasenia;
    private Rol rol;
    private List<Permiso> permisos;

    public Usuario() {
        this.permisos = new ArrayList<>();
    }

    public void agregarPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }
}
