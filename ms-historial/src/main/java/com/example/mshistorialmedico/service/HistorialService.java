package com.example.mshistorialmedico.service;

import com.example.mshistorialmedico.dto.HistorialRequestDTO;
import com.example.mshistorialmedico.model.HistorialMedico;
import com.example.mshistorialmedico.repository.HistorialMedicoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class HistorialService {

    private final HistorialMedicoRepository historialMedicoRepository;

    public HistorialService(HistorialMedicoRepository historialMedicoRepository) {
        this.historialMedicoRepository = historialMedicoRepository;
    }

    public List<HistorialMedico> findAll() {
        return historialMedicoRepository.findAll();
    }

    public HistorialMedico findById(Long id) {
        return historialMedicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Historial no encontrado"));
    }

    public List<HistorialMedico> findByMascotaId(Long mascotaId) {
        return historialMedicoRepository.findByMascotaId(mascotaId);
    }

    public HistorialMedico save(HistorialRequestDTO dto) {
        HistorialMedico historial = new HistorialMedico();
        aplicarDatos(historial, dto);
        return historialMedicoRepository.save(historial);
    }

    public HistorialMedico update(HistorialMedico existente, HistorialRequestDTO dto) {
        aplicarDatos(existente, dto);
        return historialMedicoRepository.save(existente);
    }

    public void delete(Long id) {
        if (!historialMedicoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historial no encontrado");
        }
        historialMedicoRepository.deleteById(id);
    }

    private void aplicarDatos(HistorialMedico historial, HistorialRequestDTO dto) {
        historial.setMascotaId(dto.getMascotaId());
        historial.setFechaConsulta(dto.getFechaConsulta());
        historial.setMotivoConsulta(dto.getMotivoConsulta());
        historial.setDiagnostico(dto.getDiagnostico());
        historial.setSintomas(dto.getSintomas());
        historial.setTratamiento(dto.getTratamiento());
        historial.setMedicamentosRecetados(dto.getMedicamentosRecetados());
        historial.setVeterinarioResponsable(dto.getVeterinarioResponsable());
        historial.setObservaciones(dto.getObservaciones());
    }
}
