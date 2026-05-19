package ms_auth.ms_auth.client;

import ms_auth.ms_auth.dto.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Le decimos que se conecte a ms-usuario en el puerto 8081
@FeignClient(name = "ms-usuario", url = "http://localhost:8082/api/v1/usuarios")
public interface UsuarioClient {

    // Este método irá a golpear el endpoint de ms-usuario
    @GetMapping("/buscar")
    AuthRequest buscarPorEmail(@RequestParam("email") String email);
}