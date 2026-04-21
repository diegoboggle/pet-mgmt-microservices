package com.petmanagement.ms_recordatorios.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.petmanagement.ms_recordatorios.model.Recordatorios;
import com.petmanagement.ms_recordatorios.repository.RecordatorioRepository;

@Service
@Slf4j
@RequiredArgsConstructor

public class RecordatoriosService {
    private final RecordatorioRepository recordatorioRepository;

    public Recordatorios crearRecordatorio(String destinatario, String mensaje, java.time.LocalDateTime fechaRecordatorio) {
        log.info("Creando recordatorio para {}", destinatario);
        Recordatorios nuevoRecordatorio = Recordatorios.builder()
                .destinatario(destinatario)
                .mensaje(mensaje)
                .fechaRecordatorio(fechaRecordatorio)
                .completado(false)
                .build();
        return recordatorioRepository.save(nuevoRecordatorio);}

        public List<Recordatorios> obtenerTodos(){
            return recordatorioRepository.findAll();
        }
        public List<Recordatorios> obtenerPorDestinatario(String destinatario){
            return recordatorioRepository.findByDestinatario(destinatario);
        }
    }