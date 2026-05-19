package com.example.mscitas.service;

import com.example.mscitas.dto.CitaRequestDTO;
import com.example.mscitas.model.Citas;
import com.example.mscitas.repository.CitasRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CitasService {

    private final CitasRepository citasRepository;

    public CitasService(CitasRepository citasRepository) {
        this.citasRepository = citasRepository;
    }

    public List<Citas> findAll() {
        return citasRepository.findAll();
    }

    public Citas findById(Long id) {
        return citasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));
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

    public Citas save(CitaRequestDTO dto) {
        Citas cita = new Citas();
        cita.setEstado("PENDIENTE");
        aplicarDatos(cita, dto);
        return citasRepository.save(cita);
    }

    public Citas update(Citas citaExistente, CitaRequestDTO dto) {
        aplicarDatos(citaExistente, dto);
        return citasRepository.save(citaExistente);
    }

    public Citas cambiarEstado(Citas citaExistente, String nuevoEstado) {
        citaExistente.setEstado(nuevoEstado);
        return citasRepository.save(citaExistente);
    }

    public void delete(Long id) {
        if (!citasRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada");
        }
        citasRepository.deleteById(id);
    }

    private void aplicarDatos(Citas cita, CitaRequestDTO dto) {
        cita.setMascotaId(dto.getMascotaId());
        cita.setVeterinariaId(dto.getVeterinariaId());
        cita.setFechaCita(dto.getFechaCita());
        cita.setHoraCita(dto.getHoraCita());
        cita.setMotivoCita(dto.getMotivoCita());
        cita.setObservaciones(dto.getObservaciones());
    }
}
