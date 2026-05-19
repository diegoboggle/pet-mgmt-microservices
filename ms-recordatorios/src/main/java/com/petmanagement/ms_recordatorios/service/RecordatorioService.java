package com.petmanagement.ms_recordatorios.service;

import com.petmanagement.ms_recordatorios.model.Recordatorio;
import com.petmanagement.ms_recordatorios.repository.RecordatorioRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class RecordatorioService {

    private static final Logger log = LoggerFactory.getLogger(RecordatorioService.class);

    private final RecordatorioRepository recordatorioRepository;

    public RecordatorioService(RecordatorioRepository recordatorioRepository) {
        this.recordatorioRepository = recordatorioRepository;
    }

    public Recordatorio crearRecordatorio(String destinatario, String mensaje, LocalDateTime fechaRecordatorio) {
        String destinatarioNormalizado = normalizar(destinatario);
        log.info("Creando recordatorio para {}", destinatarioNormalizado);

        Recordatorio nuevoRecordatorio = new Recordatorio();
        nuevoRecordatorio.setDestinatario(destinatarioNormalizado);
        nuevoRecordatorio.setMensaje(normalizar(mensaje));
        nuevoRecordatorio.setFechaRecordatorio(fechaRecordatorio);
        nuevoRecordatorio.setCompletado(false);

        return recordatorioRepository.save(nuevoRecordatorio);
    }

    @Transactional(readOnly = true)
    public List<Recordatorio> obtenerTodos() {
        return recordatorioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Recordatorio> obtenerPorDestinatario(String destinatario) {
        String destinatarioNormalizado = normalizar(destinatario);
        if (!StringUtils.hasText(destinatarioNormalizado)) {
            return obtenerTodos();
        }
        return recordatorioRepository.findByDestinatarioIgnoreCase(destinatarioNormalizado);
    }

    @Transactional(readOnly = true)
    public Recordatorio obtenerPorId(@NonNull Long id) {
        return recordatorioRepository.findById(id)
                .orElseThrow(() -> noEncontrado(id));
    }

    public Recordatorio actualizarEstado(@NonNull Long id, boolean completado) {
        Recordatorio recordatorio = obtenerPorId(id);
        recordatorio.setCompletado(completado);
        return recordatorioRepository.save(recordatorio);
    }

    public void eliminar(@NonNull Long id) {
        if (!recordatorioRepository.existsById(id)) {
            throw noEncontrado(id);
        }
        recordatorioRepository.deleteById(id);
    }

    private String normalizar(String valor) {
        return valor == null ? null : valor.trim();
    }

    private ResponseStatusException noEncontrado(@NonNull Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el recordatorio con id " + id);
    }
}
