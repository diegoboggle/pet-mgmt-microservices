package ms_mascotas.ms_mascotas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ms_mascotas.ms_mascotas.repository.MascotaRepository;
import ms_mascotas.ms_mascotas.service.MascotaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
class MsMascotasApplicationTests {

	@Autowired
	private MascotaService mascotaService;

	@Autowired
	private MascotaRepository mascotaRepository;

	@BeforeEach
	void limpiarBase() {
		mascotaRepository.deleteAll();
	}

	@Test
	void contextLoads() {
		assertNotNull(mascotaService);
	}

	@Test
	void buscarPorIdInexistenteDevuelveNotFound() {
		ResponseStatusException ex = assertThrows(
				ResponseStatusException.class,
				() -> mascotaService.buscarPorId(999L));

		assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
	}

	@Test
	void listarPorUsuarioSinMascotasDevuelveListaVacia() {
		assertTrue(mascotaService.listarPorUsuarioId(999L).isEmpty());
	}

}
