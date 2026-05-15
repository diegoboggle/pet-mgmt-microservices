package com.petmanagement.ms_notificaciones.service;

import com.petmanagement.ms_notificaciones.model.Notificacion;
import com.petmanagement.ms_notificaciones.repository.NotificacionRepository;
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
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public Notificacion crearNotificacion(String destinatario, String mensaje, String tipo) {
        String destinatarioNormalizado = normalizar(destinatario);
        log.info("Creando notificación para: {}", destinatarioNormalizado);

        Notificacion notificacion = Notificacion.builder()
                .destinatario(destinatarioNormalizado)
                .mensaje(normalizar(mensaje))
                .tipo(normalizar(tipo))
                .fechaEnvio(LocalDateTime.now())
                .enviada(true)
                .build();

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
    public Notificacion obtenerPorId(Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> noEncontrada(id));
    }

    public Notificacion actualizarEstado(Long id, boolean enviada) {
        Notificacion notificacion = obtenerPorId(id);
        notificacion.setEnviada(enviada);
        return notificacionRepository.save(notificacion);
    }

    public void eliminar(Long id) {
        if (!notificacionRepository.existsById(id)) {
            throw noEncontrada(id);
        }
        notificacionRepository.deleteById(id);
    }

    private String normalizar(String valor) {
        return valor == null ? null : valor.trim();
    }

    private ResponseStatusException noEncontrada(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la notificación con id " + id);
    }
}
