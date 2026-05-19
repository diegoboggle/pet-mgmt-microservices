package ms_auth.ms_auth.service;

import lombok.extern.slf4j.Slf4j;
import ms_auth.ms_auth.client.UsuarioClient;
import ms_auth.ms_auth.dto.AuthRequest;
import ms_auth.ms_auth.client.TokenResponse;
import ms_auth.ms_auth.security.JwtProvider;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Slf4j 
@Service
public class AuthService {

    private final UsuarioClient usuarioClient;
    private final JwtProvider jwtProvider;

    public AuthService(UsuarioClient usuarioClient, JwtProvider jwtProvider) {
        this.usuarioClient = usuarioClient;
        this.jwtProvider = jwtProvider;
    }

    public TokenResponse login(AuthRequest request) {
        log.info("Intento de login recibido para el email: {}", request.getEmail());

        AuthRequest usuario = usuarioClient.buscarPorEmail(request.getEmail());

        if (!Objects.equals(usuario.getPassword(), request.getPassword())) {
            log.warn("Intento de login fallido. Contraseña incorrecta para: {}", request.getEmail());
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String token = jwtProvider.generateToken(usuario.getEmail(), usuario.getRol());
        log.info("Login exitoso. Token generado correctamente.");

        return new TokenResponse(token);
    }
}
