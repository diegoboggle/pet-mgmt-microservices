package com.example.mscitas.service;

import com.example.mscitas.dto.CitaRequestDTO;
import com.example.mscitas.model.Citas;
import com.example.mscitas.repository.CitasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CitasService {

    @Autowired
    private CitasRepository citasRepository;

    public List<Citas> findAll() {
        return citasRepository.findAll();
    }
 
    public Citas findById(Long id) {
        return citasRepository.findById(id).get();
    }
 
    public List<Citas> findByMascotaId(Long mascotaId) {
        return citasRepository.findByMascotaId(mascotaId);
    }
 
    public List<Citas> findByEstado(String estado) {
        return citasRepository.findByEstado(estado);
    }
 
    public List<Citas> findByVeterinariaId(Long veterinariaId) {
        return citasRepository.findByVeterinariaId(veterinariaId);
    }
 
    // Crea una cita nueva desde un DTO
    // La regla de negocio (estado = PENDIENTE) se aplica aquí en el Service, no en el Controller
    public Citas save(CitaRequestDTO dto) {
        Citas cita = new Citas();
        cita.setMascotaId(dto.getMascotaId());
        cita.setVeterinariaId(dto.getVeterinariaId());
        cita.setFechaCita(dto.getFechaCita());
        cita.setHoraCita(dto.getHoraCita());
        cita.setMotivoCita(dto.getMotivoCita());
        cita.setEstado("PENDIENTE"); // Regla de negocio: toda cita nueva parte como PENDIENTE
        cita.setObservaciones(dto.getObservaciones());
        return citasRepository.save(cita);
    }
 
    // Actualiza una cita existente con los datos del DTO
    public Citas update(Citas citaExistente, CitaRequestDTO dto) {
        citaExistente.setMascotaId(dto.getMascotaId());
        citaExistente.setVeterinariaId(dto.getVeterinariaId());
        citaExistente.setFechaCita(dto.getFechaCita());
        citaExistente.setHoraCita(dto.getHoraCita());
        citaExistente.setMotivoCita(dto.getMotivoCita());
        citaExistente.setObservaciones(dto.getObservaciones());
        return citasRepository.save(citaExistente);
    }
 
    // Cambia solo el estado de una cita (PENDIENTE → CONFIRMADA → COMPLETADA / CANCELADA)
    public Citas cambiarEstado(Citas citaExistente, String nuevoEstado) {
        citaExistente.setEstado(nuevoEstado);
        return citasRepository.save(citaExistente);
    }
 
    public void delete(Long id) {
        citasRepository.deleteById(id);
    }
}
