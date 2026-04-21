package com.petmanagement.ms_recordatorios.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.petmanagement.ms_recordatorios.model.Recordatorio;
import com.petmanagement.ms_recordatorios.repository.RecordatorioRepository;

@Service
@Slf4j
@RequiredArgsConstructor

public class RecordatoriosService {
    private final RecordatorioRepository recordatorioRepository;

    public Recordatorio crearRecordatorio(Recordatorio recordatorio) {
        log.info("Creando recordatorio para {}", destinatario);
        Recordatorio recordatorio = Recordatorio.builder()
                .destinatario(destinatario)
                .mensaje(mensaje)
                .fechaRecordatorio(fechaRecordatorio)
                .completado(false)
                .build();
        return recordatorioRepository.save(recordatorio);}

        public List<Recordatorio> obtenerTodos(){
            return recordatorioRepository.findAll();
        }
        public List<Recordatorio> obtenerPorDestinatario(String destinatario){
            return recordatorioRepository.findByDestinatario(destinatario);
        }
    }