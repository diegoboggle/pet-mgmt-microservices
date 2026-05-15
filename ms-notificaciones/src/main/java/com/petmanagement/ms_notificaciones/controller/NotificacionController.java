package com.petmanagement.ms_notificaciones.controller;

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
import com.petmanagement.ms_notificaciones.dto.NotificacionRequestDTO;
import com.petmanagement.ms_notificaciones.model.Notificacion;
import com.petmanagement.ms_notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private static final Logger log = LoggerFactory.getLogger(NotificacionController.class);

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(@Valid @RequestBody NotificacionRequestDTO request) {
        log.info("POST /api/notificaciones - Recibida petición para: {}", request.getDestinatario());

        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.crearNotificacion(
                request.getDestinatario(),
                request.getMensaje(),
                request.getTipo()));
    }

    @GetMapping
    public ResponseEntity<List<Notificacion>> obtenerNotificaciones(
            @RequestParam(required = false) String destinatario) {

        if (StringUtils.hasText(destinatario)) {
            return ResponseEntity.ok(notificacionService.obtenerPorDestinatario(destinatario));
        }
        return ResponseEntity.ok(notificacionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.obtenerPorId(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Notificacion> actualizarEstado(
            @PathVariable Long id,
            @RequestParam boolean enviada) {

        return ResponseEntity.ok(notificacionService.actualizarEstado(id, enviada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
