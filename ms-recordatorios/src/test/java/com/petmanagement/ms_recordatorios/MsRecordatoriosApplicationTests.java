package com.petmanagement.ms_recordatorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.petmanagement.ms_recordatorios.model.Recordatorio;
import com.petmanagement.ms_recordatorios.repository.RecordatorioRepository;
import com.petmanagement.ms_recordatorios.service.RecordatorioService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MsRecordatoriosApplication.class, properties = {
		"spring.datasource.url=jdbc:h2:mem:recordatoriosdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.jpa.show-sql=false",
		"debug=false",
		"logging.level.org.springframework=INFO",
		"logging.level.org.hibernate.SQL=OFF"
})
class MsRecordatoriosApplicationTests {

	@Autowired
	private RecordatorioService recordatorioService;

	@Autowired
	private RecordatorioRepository recordatorioRepository;

	@BeforeEach
	void limpiarBase() {
		recordatorioRepository.deleteAll();
	}

	@Test
	void contextLoads() {
		assertNotNull(recordatorioService);
	}

	@Test
	void creaRecordatorioNormalizadoYLoBuscaSinImportarMayusculas() {
		LocalDateTime fecha = LocalDateTime.now().plusDays(1);

		Recordatorio recordatorio = recordatorioService.crearRecordatorio(
				"  Cliente@Test.com  ",
				"  Control veterinario  ",
				fecha);

		assertEquals("Cliente@Test.com", recordatorio.getDestinatario());
		assertEquals("Control veterinario", recordatorio.getMensaje());
		assertEquals(fecha, recordatorio.getFechaRecordatorio());
		assertFalse(recordatorio.isCompletado());

		List<Recordatorio> resultado = recordatorioService.obtenerPorDestinatario("cliente@test.com");

		assertEquals(1, resultado.size());
		assertEquals(recordatorio.getId(), resultado.getFirst().getId());
	}

	@Test
	void actualizaEstadoDeCompletado() {
		Recordatorio recordatorio = recordatorioService.crearRecordatorio(
				"cliente",
				"mensaje",
				LocalDateTime.now().plusDays(1));

		Recordatorio actualizado = recordatorioService.actualizarEstado(recordatorio.getId(), true);

		assertTrue(actualizado.isCompletado());
	}

}
