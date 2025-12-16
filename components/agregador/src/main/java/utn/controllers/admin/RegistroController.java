package utn.controllers.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.models.dtos.RegistroUsuarioDTO;
import utn.services.RegistroUsuarioService;

@RestController
@RequestMapping("/api/auth")
public class RegistroController {

    private final RegistroUsuarioService registroUsuarioService;

    public RegistroController(RegistroUsuarioService registroUsuarioService) {
        this.registroUsuarioService = registroUsuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody RegistroUsuarioDTO dto) {
        registroUsuarioService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente");
    }
}