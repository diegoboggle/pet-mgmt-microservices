package com.petmanagement.ms_notificaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.petmanagement.ms_notificaciones.model.Notificacion;
import com.petmanagement.ms_notificaciones.repository.NotificacionRepository;
import com.petmanagement.ms_notificaciones.service.NotificacionService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MsNotificacionesApplication.class, properties = {
		"spring.datasource.url=jdbc:h2:mem:notificacionesdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.jpa.show-sql=false",
		"debug=false",
		"logging.level.org.springframework=INFO",
		"logging.level.org.hibernate.SQL=OFF"
})
class MsNotificacionesApplicationTests {

	@Autowired
	private NotificacionService notificacionService;

	@Autowired
	private NotificacionRepository notificacionRepository;

	@BeforeEach
	void limpiarBase() {
		notificacionRepository.deleteAll();
	}

	@Test
	void contextLoads() {
		assertNotNull(notificacionService);
	}

	@Test
	void creaNotificacionNormalizadaYLaBuscaSinImportarMayusculas() {
		Notificacion notificacion = notificacionService.crearNotificacion(
				"  Usuario@Test.com  ",
				"  Vacuna lista  ",
				"  INFO  ");

		assertEquals("Usuario@Test.com", notificacion.getDestinatario());
		assertEquals("Vacuna lista", notificacion.getMensaje());
		assertEquals("INFO", notificacion.getTipo());
		assertTrue(notificacion.isEnviada());
		assertNotNull(notificacion.getFechaEnvio());

		List<Notificacion> resultado = notificacionService.obtenerPorDestinatario("usuario@test.com");

		assertEquals(1, resultado.size());
		assertEquals(notificacion.getId(), resultado.getFirst().getId());
	}

	@Test
	void actualizaEstadoDeEnvio() {
		Notificacion notificacion = notificacionService.crearNotificacion("usuario", "mensaje", "INFO");

		Notificacion actualizada = notificacionService.actualizarEstado(notificacion.getId(), false);

		assertFalse(actualizada.isEnviada());
	}

}
