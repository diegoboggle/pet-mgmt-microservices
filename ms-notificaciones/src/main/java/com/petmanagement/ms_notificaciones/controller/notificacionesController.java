package com.petmanagement.ms_notificaciones.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmanagement.ms_notificaciones.model.notificaciones;
import com.petmanagement.ms_notificaciones.service.notificacionesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
@Slf4j
public class notificacionesController {

    private final notificacionesService notificacionesService;

    @PostMapping
    public ResponseEntity<notificaciones> crear(@RequestBody notificaciones notificacion) {
        log.info("POST /api/notificaciones");
        return ResponseEntity.ok(notificacionesService.crearNotificacion(
                notificacion.getDestinatario(),
                notificacion.getMensaje(),
                notificacion.getTipo()
        ));
    }

    @GetMapping
    public ResponseEntity<List<notificaciones>> obtenerTodas() {
        return ResponseEntity.ok(notificacionesService.obtenerTodas());
    }

    @GetMapping("/{destinatario}")
    public ResponseEntity<List<notificaciones>> obtenerPorDestinatario(@PathVariable String destinatario) {
        return ResponseEntity.ok(notificacionesService.obtenerPorDestinatario(destinatario));
    }
}
