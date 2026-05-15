package com.petmanagement.ms_reportes.service;

import com.petmanagement.ms_reportes.model.Reporte;
import com.petmanagement.ms_reportes.repository.ReporteRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReporteService {

    private final ReporteRepository reporteRepository;

    public Reporte crearReporte(String tipoReporte, String solicitante, String contenido) {
        String tipoReporteNormalizado = normalizar(tipoReporte);
        String solicitanteNormalizado = normalizar(solicitante);
        log.info("Generando reporte de tipo {} para {}", tipoReporteNormalizado, solicitanteNormalizado);

        Reporte nuevoReporte = Reporte.builder()
                .tipoReporte(tipoReporteNormalizado)
                .solicitante(solicitanteNormalizado)
                .contenido(normalizar(contenido))
                .fechaGeneracion(LocalDateTime.now())
                .build();

        return reporteRepository.save(nuevoReporte);
    }

    @Transactional(readOnly = true)
    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Reporte> obtenerPorTipo(String tipoReporte) {
        String tipoReporteNormalizado = normalizar(tipoReporte);
        if (!StringUtils.hasText(tipoReporteNormalizado)) {
            return obtenerTodos();
        }
        return reporteRepository.findByTipoReporteIgnoreCase(tipoReporteNormalizado);
    }

    @Transactional(readOnly = true)
    public List<Reporte> obtenerPorSolicitante(String solicitante) {
        String solicitanteNormalizado = normalizar(solicitante);
        if (!StringUtils.hasText(solicitanteNormalizado)) {
            return obtenerTodos();
        }
        return reporteRepository.findBySolicitanteIgnoreCase(solicitanteNormalizado);
    }

    @Transactional(readOnly = true)
    public Reporte obtenerPorId(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> noEncontrado(id));
    }

    public void eliminar(Long id) {
        if (!reporteRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        reporteRepository.deleteById(id);
    }

    private String normalizar(String valor) {
        return valor == null ? null : valor.trim();
    }

    private ResponseStatusException noEncontrado(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el reporte con id " + id);
    }
}
