package com.petmanagement.ms_notificaciones.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.petmanagement.ms_notificaciones.model.notificaciones;
import com.petmanagement.ms_notificaciones.repository.notificacionesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class notificacionesService {

    private final notificacionesRepository notificacionesRepository;

    public notificaciones crearNotificacion(String destinatario, String mensaje, String tipo) {
        log.info("Creando notificación para: {}", destinatario);

        notificaciones notificacion = notificaciones.builder()
                .destinatario(destinatario)
                .mensaje(mensaje)
                .tipo(tipo)
                .fechaEnvio(LocalDateTime.now())
                .enviada(true)
                .build();

        return notificacionesRepository.save(notificacion);
    }

    public List<notificaciones> obtenerTodas() {
        return notificacionesRepository.findAll();
    }

    public List<notificaciones> obtenerPorDestinatario(String destinatario) {
        return notificacionesRepository.findByDestinatario(destinatario);
    }
}
