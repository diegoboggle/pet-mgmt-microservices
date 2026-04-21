package com.petmanagement.ms_recordatorios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petmanagement.ms_recordatorios.dto.RecordatorioRequestDTO;
import com.petmanagement.ms_recordatorios.model.Recordatorio;
import com.petmanagement.ms_recordatorios.service.RecordatorioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/recordatorios")
@RequiredArgsConstructor
@Slf4j
public class RecordatorioController {

    private final RecordatorioService recordatoriosService;

    @PostMapping
    public ResponseEntity<Recordatorio> crear(@Valid @RequestBody RecordatorioRequestDTO request) {
        log.info("POST /api/recordatorios - Recibida petición para: {}", request.getDestinatario());

        return ResponseEntity.ok(recordatoriosService.crearRecordatorio(
                request.getDestinatario(),
                request.getMensaje(),
                request.getFechaRecordatorio()));
    }

    @GetMapping
    public ResponseEntity<List<Recordatorio>> obtenerRecordatorios(
            @RequestParam(required = false) String destinatario) {

        if (destinatario != null && !destinatario.trim().isEmpty()) {
            return ResponseEntity.ok(recordatoriosService.obtenerPorDestinatario(destinatario));
        }
        return ResponseEntity.ok(recordatoriosService.obtenerTodos());
    }
}