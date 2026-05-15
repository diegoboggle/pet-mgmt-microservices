package com.petmanagement.ms_recordatorios.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.petmanagement.ms_recordatorios.dto.RecordatorioRequestDTO;
import com.petmanagement.ms_recordatorios.model.Recordatorio;
import com.petmanagement.ms_recordatorios.service.RecordatorioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/recordatorios")
public class RecordatorioController {

    private static final Logger log = LoggerFactory.getLogger(RecordatorioController.class);

    private final RecordatorioService recordatorioService;

    public RecordatorioController(RecordatorioService recordatorioService) {
        this.recordatorioService = recordatorioService;
    }

    @PostMapping
    public ResponseEntity<Recordatorio> crear(@Valid @RequestBody RecordatorioRequestDTO request) {
        log.info("POST /api/recordatorios - Recibida petición para: {}", request.getDestinatario());

        return ResponseEntity.status(HttpStatus.CREATED).body(recordatorioService.crearRecordatorio(
                request.getDestinatario(),
                request.getMensaje(),
                request.getFechaRecordatorio()));
    }

    @GetMapping
    public ResponseEntity<List<Recordatorio>> obtenerRecordatorios(
            @RequestParam(required = false) String destinatario) {

        if (StringUtils.hasText(destinatario)) {
            return ResponseEntity.ok(recordatorioService.obtenerPorDestinatario(destinatario));
        }
        return ResponseEntity.ok(recordatorioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recordatorio> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recordatorioService.obtenerPorId(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Recordatorio> actualizarEstado(
            @PathVariable Long id,
            @RequestParam(defaultValue = "true") boolean completado) {

        return ResponseEntity.ok(recordatorioService.actualizarEstado(id, completado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recordatorioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
