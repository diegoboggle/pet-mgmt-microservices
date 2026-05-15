package com.petmanagement.ms_reportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.petmanagement.ms_reportes.model.Reporte;
import com.petmanagement.ms_reportes.repository.ReporteRepository;
import com.petmanagement.ms_reportes.service.ReporteService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest(classes = MsReportesApplication.class, properties = {
		"spring.datasource.url=jdbc:h2:mem:reportesdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.jpa.show-sql=false",
		"debug=false",
		"logging.level.org.springframework=INFO",
		"logging.level.org.hibernate.SQL=OFF"
})
class MsReportesApplicationTests {

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private ReporteRepository reporteRepository;

	@BeforeEach
	void limpiarBase() {
		reporteRepository.deleteAll();
	}

	@Test
	void contextLoads() {
		assertNotNull(reporteService);
	}

	@Test
	void creaReporteNormalizadoYLoFiltraPorTipoOSolicitante() {
		Reporte reporte = reporteService.crearReporte(
				"  Mascotas Perdidas  ",
				"  Admin@Test.com  ",
				"  Contenido del reporte  ");

		assertEquals("Mascotas Perdidas", reporte.getTipoReporte());
		assertEquals("Admin@Test.com", reporte.getSolicitante());
		assertEquals("Contenido del reporte", reporte.getContenido());
		assertNotNull(reporte.getFechaGeneracion());

		List<Reporte> porTipo = reporteService.obtenerPorTipo("mascotas perdidas");
		List<Reporte> porSolicitante = reporteService.obtenerPorSolicitante("admin@test.com");

		assertEquals(1, porTipo.size());
		assertEquals(reporte.getId(), porTipo.getFirst().getId());
		assertEquals(1, porSolicitante.size());
		assertEquals(reporte.getId(), porSolicitante.getFirst().getId());
	}

	@Test
	void eliminaReportePorId() {
		Reporte reporte = reporteService.crearReporte("Adopciones", "admin", "contenido");

		reporteService.eliminar(reporte.getId());

		assertThrows(ResponseStatusException.class, () -> reporteService.obtenerPorId(reporte.getId()));
	}

}
