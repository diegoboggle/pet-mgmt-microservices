package ms_usuario.usuario.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ms_usuario.usuario.dto.UsuarioDTO;
import ms_usuario.usuario.model.Usuario;
import ms_usuario.usuario.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(UsuarioDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya se encuentra registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol());

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorEmail(String email){
        log.info("Buscando usuario en la base de datos con email: {}", email);

        return usuarioRepository.findByEmail(email)
        .orElseThrow(() -> {
            log.warn("Intento de búsqueda fallido. No existe el email: {}", email);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        });
    }
}
