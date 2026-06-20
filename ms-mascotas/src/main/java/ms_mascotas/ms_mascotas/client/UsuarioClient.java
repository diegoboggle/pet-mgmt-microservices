package ms_mascotas.ms_mascotas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser igual al spring.application.name de ms-usuario en Eureka
@FeignClient(name = "ms-usuario") 
public interface UsuarioClient {

    @GetMapping("/api/v1/usuarios/{id}")
    Object obtenerUsuarioPorId(@PathVariable("id") Long id);
}