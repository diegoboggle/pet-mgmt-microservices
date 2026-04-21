package com.petmanagement.ms_reportes.service;

import com.petmanagement.ms_reportes.model.Reporte;
import com.petmanagement.ms_reportes.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteService {

    // Variable en minúscula para evitar los errores de llamado estático
    private final ReporteRepository reporteRepository;

    public Reporte crearReporte(String tipoReporte, String solicitante, String contenido) {
        log.info("Generando reporte de tipo {} para {}", tipoReporte, solicitante);

        Reporte nuevoReporte = Reporte.builder()
                .tipoReporte(tipoReporte)
                .solicitante(solicitante)
                .contenido(contenido)
                .fechaGeneracion(LocalDateTime.now()) // Se asigna automáticamente la fecha actual
                .build();

        return reporteRepository.save(nuevoReporte);
    }

    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    public List<Reporte> obtenerPorTipo(String tipoReporte) {
        return reporteRepository.findByTipoReporte(tipoReporte);
    }
}