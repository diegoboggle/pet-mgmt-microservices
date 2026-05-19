package ms_usuario.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ms_usuario.usuario.dto.UsuarioDTO;
import ms_usuario.usuario.repository.UsuarioRepository;
import ms_usuario.usuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
class UsuarioApplicationTests {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeEach
	void limpiarBase() {
		usuarioRepository.deleteAll();
	}

	@Test
	void contextLoads() {
		assertNotNull(usuarioService);
	}

	@Test
	void rechazaEmailDuplicadoConConflict() {
		usuarioService.crearUsuario(usuarioDto("cliente@test.com"));

		ResponseStatusException ex = assertThrows(
				ResponseStatusException.class,
				() -> usuarioService.crearUsuario(usuarioDto("cliente@test.com")));

		assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
	}

	@Test
	void buscarPorEmailInexistenteDevuelveNotFound() {
		ResponseStatusException ex = assertThrows(
				ResponseStatusException.class,
				() -> usuarioService.buscarPorEmail("nadie@test.com"));

		assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
	}

	private UsuarioDTO usuarioDto(String email) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNombre("Cliente");
		dto.setApellido("Prueba");
		dto.setEmail(email);
		dto.setTelefono("123456789");
		dto.setDireccion("Direccion de prueba");
		dto.setPassword("secreto123");
		dto.setRol("CLIENTE");
		return dto;
	}

}
