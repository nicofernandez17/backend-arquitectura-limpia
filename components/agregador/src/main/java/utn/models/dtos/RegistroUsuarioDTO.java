package utn.models.dtos;

import lombok.Data;

@Data
public class RegistroUsuarioDTO {
    private String nombre;
    private String nombreDeUsuario;
    private String contrasenia;
}