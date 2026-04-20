package com.petmanagement.ms_notificaciones.service;

import com.petmanagement.ms_notificaciones.model.Notificacion;
import com.petmanagement.ms_notificaciones.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacionService {

    // Nota cómo la clase es PascalCase (NotificacionRepository)
    // pero la variable se mantiene en camelCase (notificacionRepository)
    private final NotificacionRepository notificacionRepository;

    public Notificacion crearNotificacion(String destinatario, String mensaje, String tipo) {
        log.info("Creando notificación para: {}", destinatario);

        Notificacion notificacion = Notificacion.builder()
                .destinatario(destinatario)
                .mensaje(mensaje)
                .tipo(tipo)
                .fechaEnvio(LocalDateTime.now())
                .enviada(true)
                .build();

        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }

    public List<Notificacion> obtenerPorDestinatario(String destinatario) {
        return notificacionRepository.findByDestinatario(destinatario);
    }
}