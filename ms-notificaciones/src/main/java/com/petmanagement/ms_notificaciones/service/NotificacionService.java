package com.petmanagement.ms_notificaciones.service;

import com.petmanagement.ms_notificaciones.model.Notificacion;
import com.petmanagement.ms_notificaciones.repository.NotificacionRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class NotificacionService {

    private static final Logger log = LoggerFactory.getLogger(NotificacionService.class);

    private final NotificacionRepository notificacionRepository;

    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public Notificacion crearNotificacion(String destinatario, String mensaje, String tipo) {
        String destinatarioNormalizado = normalizar(destinatario);
        log.info("Creando notificación para: {}", destinatarioNormalizado);

        Notificacion notificacion = new Notificacion();
        notificacion.setDestinatario(destinatarioNormalizado);
        notificacion.setMensaje(normalizar(mensaje));
        notificacion.setTipo(normalizar(tipo));
        notificacion.setFechaEnvio(LocalDateTime.now());
        notificacion.setEnviada(true);

        return notificacionRepository.save(notificacion);
    }

    @Transactional(readOnly = true)
    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Notificacion> obtenerPorDestinatario(String destinatario) {
        String destinatarioNormalizado = normalizar(destinatario);
        if (!StringUtils.hasText(destinatarioNormalizado)) {
            return obtenerTodas();
        }
        return notificacionRepository.findByDestinatarioIgnoreCase(destinatarioNormalizado);
    }

    @Transactional(readOnly = true)
    public Notificacion obtenerPorId(@NonNull Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> noEncontrada(id));
    }

    public Notificacion actualizarEstado(@NonNull Long id, boolean enviada) {
        Notificacion notificacion = obtenerPorId(id);
        notificacion.setEnviada(enviada);
        return notificacionRepository.save(notificacion);
    }

    public void eliminar(@NonNull Long id) {
        if (!notificacionRepository.existsById(id)) {
            throw noEncontrada(id);
        }
        notificacionRepository.deleteById(id);
    }

    private String normalizar(String valor) {
        return valor == null ? null : valor.trim();
    }

    private ResponseStatusException noEncontrada(@NonNull Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la notificación con id " + id);
    }
}
