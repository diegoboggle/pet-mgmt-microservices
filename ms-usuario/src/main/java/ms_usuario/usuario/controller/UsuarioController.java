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


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuario", description = "API para la gestión de usuarios en el sistema")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Registra un usuario en la base de datos validando sus datos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error de validación en los datos"),
        @ApiResponse(responseCode = "409", description = "El correo electrónico ya existe")
    })
    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody UsuarioDTO dto){
        log.info("Petición REST recibida para registro de usuario con email:{}", dto.getEmail());
        Usuario usuarioCreado = usuarioService.crearUsuario(dto);
        log.info("Usuario creado exitosamente con ID: {}", usuarioCreado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @Operation(summary = "Listar todos los usuarios", description = "Retorna una lista de todos los usuarios registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios retornada")
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @Operation(summary = "Buscar usuario por email", description = "Retorna los datos de un usuario dado su correo electrónico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/buscar")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
    }
    
    @Operation(summary = "Ver perfil completo", description = "Retorna un usuario y la lista de sus mascotas consultando al microservicio de mascotas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Perfil completo encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}/mascotas")
    public ResponseEntity<Map<String, Object>> verPerfilCompleto(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioConSusMascotas(id));
    }

    @Operation(summary = "Buscar usuario por ID", description = "Retorna los datos de un usuario dado su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }
}
