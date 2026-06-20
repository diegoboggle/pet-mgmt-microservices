package ms_auth.ms_auth.service;

import lombok.extern.slf4j.Slf4j;
import ms_auth.ms_auth.client.UsuarioClient;
import ms_auth.ms_auth.dto.AuthRequest;
import ms_auth.ms_auth.client.TokenResponse;
import ms_auth.ms_auth.security.JwtProvider;
import java.util.Objects;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j 
@Service
public class AuthService {

    private final UsuarioClient usuarioClient;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioClient usuarioClient, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.usuarioClient = usuarioClient;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenResponse login(AuthRequest request) {
        log.info("Intento de login recibido para el email: {}", request.getEmail());

        AuthRequest usuario = usuarioClient.buscarPorEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            log.warn("Intento de login fallido. Contraseña incorrecta para: {}", request.getEmail());
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String token = jwtProvider.generateToken(usuario.getEmail(), usuario.getRol());
        log.info("Login exitoso. Token generado correctamente.");

        return new TokenResponse(token);
    }
}
