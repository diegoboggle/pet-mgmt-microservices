package com.example.mshistorialmedico.service;

import com.example.mshistorialmedico.model.HistorialMedico;
import com.example.mshistorialmedico.dto.HistorialRequestDTO;
import com.example.mshistorialmedico.repository.HistorialMedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class HistorialService {
    
    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    //retorna todos los registros de historial medico
    public List<HistorialMedico> findAll(){
        return historialMedicoRepository.findAll();
    }

    //retorna un registro por su ID
    public HistorialMedico findById(Long id){
        return historialMedicoRepository.findById(id).get();
    }

    //retorna todo el historial clinico de una mascota especifica
    public List<HistorialMedico> findByMascotaId(Long mascotaId){
        return historialMedicoRepository.findByMascotaId(mascotaId);
    }

    //Guarda un registro nuevo o actualiza uno existente
    public HistorialMedico save(HistorialMedico historialMedico){
        return historialMedicoRepository.save(historialMedico);
    }

    // Convierte el DTO a entidad y la guarda (para POST)
    public HistorialMedico save(HistorialRequestDTO dto) {
        HistorialMedico historial = new HistorialMedico();
        historial.setMascotaId(dto.getMascotaId());
        historial.setFechaConsulta(dto.getFechaConsulta());
        historial.setMotivoConsulta(dto.getMotivoConsulta());
        historial.setDiagnostico(dto.getDiagnostico());
        historial.setSintomas(dto.getSintomas());
        historial.setTratamiento(dto.getTratamiento());
        historial.setMedicamentosRecetados(dto.getMedicamentosRecetados());
        historial.setVeterinarioResponsable(dto.getVeterinarioResponsable());
        historial.setObservaciones(dto.getObservaciones());
        return historialMedicoRepository.save(historial);
    }
 
    // Actualiza la entidad existente con los datos del DTO (para PUT)
    public HistorialMedico update(HistorialMedico existente, HistorialRequestDTO dto) {
        existente.setMascotaId(dto.getMascotaId());
        existente.setFechaConsulta(dto.getFechaConsulta());
        existente.setMotivoConsulta(dto.getMotivoConsulta());
        existente.setDiagnostico(dto.getDiagnostico());
        existente.setSintomas(dto.getSintomas());
        existente.setTratamiento(dto.getTratamiento());
        existente.setMedicamentosRecetados(dto.getMedicamentosRecetados());
        existente.setVeterinarioResponsable(dto.getVeterinarioResponsable());
        existente.setObservaciones(dto.getObservaciones());
        return historialMedicoRepository.save(existente);
    }

    //Elimina un registro por su ID
    public void delete(Long id){
        historialMedicoRepository.deleteById(id);
    }
}
