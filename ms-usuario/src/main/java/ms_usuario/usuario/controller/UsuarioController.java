package ms_usuario.usuario.controller;

import ms_usuario.usuario.dto.UsuarioDTO;
import ms_usuario.usuario.model.Usuario;
import ms_usuario.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// ¡Estos 3 imports son la magia de HATEOAS!
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    // --- MÉTODO ACTUALIZADO CON HATEOAS ---
    @GetMapping("/buscar")
    public ResponseEntity<EntityModel<Usuario>> buscarPorEmail(@RequestParam String email) {
        
        // 1. Obtenemos el usuario desde el servicio
        Usuario usuario = Optional.ofNullable(usuarioService.buscarPorEmail(email))
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No existe usuario con email: " + email
            ));

        // 2. Envolvemos el usuario en el modelo de HATEOAS
        EntityModel<Usuario> recurso = EntityModel.of(usuario);

        // 3. Agregamos el enlace "self" (hacia este mismo método)
        recurso.add(linkTo(methodOn(this.getClass()).buscarPorEmail(email)).withSelfRel());

        // 4. Agregamos el enlace "mis-mascotas" (hacia el método verPerfilCompleto)
        recurso.add(linkTo(methodOn(this.getClass()).verPerfilCompleto(usuario.getId())).withRel("mis-mascotas"));

        // 5. Retornamos el recurso con los links inyectados
        return ResponseEntity.ok(recurso);
    }
    

    @GetMapping("/{id}/mascotas")
    public ResponseEntity<Map<String, Object>> verPerfilCompleto(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioConSusMascotas(id));
    }
    
}