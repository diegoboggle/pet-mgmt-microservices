package ms_usuario.usuario.controller;

import ms_usuario.usuario.dto.UsuarioDTO;
import ms_usuario.usuario.model.Usuario;
import ms_usuario.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody UsuarioDTO dto){
        log.info("Petición REST recibida para registro de usuario con email:{}", dto.getEmail());
        Usuario usuarioCreado = usuarioService.crearUsuario(dto);
        log.info("Usuario creado exitosamente con ID: {}", usuarioCreado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/buscar")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
    }
    

    @GetMapping("/{id}/mascotas")
    public ResponseEntity<Map<String, Object>> verPerfilCompleto(@PathVariable Long id) {
    return ResponseEntity.ok(usuarioService.obtenerUsuarioConSusMascotas(id));
}
    
}   
