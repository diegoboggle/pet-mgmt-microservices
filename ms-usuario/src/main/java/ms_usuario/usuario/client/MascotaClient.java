package ms_usuario.usuario.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "ms-mascotas", fallback = MascotaClientFallback.class) 
public interface MascotaClient {

    @GetMapping("/api/v1/mascotas/usuario/{usuarioId}")
    List<Object> listarPorUsuarioId(@PathVariable("usuarioId") Long usuarioId);
}
