package ms_auth.ms_auth.client;

import ms_auth.ms_auth.dto.AuthRequest;
import org.springframework.stereotype.Component;

@Component
public class UsuarioClientFallback implements UsuarioClient {
    @Override
    public AuthRequest buscarPorEmail(String email) {
        
        throw new RuntimeException("Servicio de usuarios no disponible temporalmente.");
    }
}
