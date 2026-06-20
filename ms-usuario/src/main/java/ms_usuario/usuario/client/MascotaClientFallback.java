package ms_usuario.usuario.client;

import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

@Component
public class MascotaClientFallback implements MascotaClient {
    @Override
    public List<Object> listarPorUsuarioId(Long usuarioId) {
        
        return Collections.emptyList();
    }
}
