package com.petmanagement.ms_reportes.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.petmanagement.ms_reportes.dto.ReporteRequestDTO;
import com.petmanagement.ms_reportes.model.Reporte;
import com.petmanagement.ms_reportes.service.ReporteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@Slf4j
public class ReporteController {

    private final ReporteService reporteService;

    @PostMapping
    public ResponseEntity<Reporte> crear(@Valid @RequestBody ReporteRequestDTO request) {
        log.info("POST /api/reportes - Recibida petición para reporte: {}", request.getTipoReporte());

        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.crearReporte(
                request.getTipoReporte(),
                request.getSolicitante(),
                request.getContenido()));
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> obtenerReportes(
            @RequestParam(required = false) String tipoReporte,
            @RequestParam(required = false) String solicitante) {

        if (StringUtils.hasText(tipoReporte)) {
            return ResponseEntity.ok(reporteService.obtenerPorTipo(tipoReporte));
        }
        if (StringUtils.hasText(solicitante)) {
            return ResponseEntity.ok(reporteService.obtenerPorSolicitante(solicitante));
        }
        return ResponseEntity.ok(reporteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reporteService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
