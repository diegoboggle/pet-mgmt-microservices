package ms_auth.ms_auth.client;

import ms_auth.ms_auth.dto.AuthRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ms-usuario", url = "${usuarios.service.url}")
public interface UsuarioClient {

    @GetMapping("/buscar")
    AuthRequest buscarPorEmail(@RequestParam("email") String email);
}
