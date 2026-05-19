package com.example.msvacunas.service;

import com.example.msvacunas.dto.VacunaRequestDTO;
import com.example.msvacunas.model.Vacunas;
import com.example.msvacunas.repository.VacunasRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class VacunasService {

    private final VacunasRepository vacunasRepository;

    public VacunasService(VacunasRepository vacunasRepository) {
        this.vacunasRepository = vacunasRepository;
    }

    public List<Vacunas> findAll() {
        return vacunasRepository.findAll();
    }

    public Vacunas findById(Long id) {
        return vacunasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacuna no encontrada"));
    }

    public List<Vacunas> findByMascotaId(Long mascotaId) {
        return vacunasRepository.findByMascotaId(mascotaId);
    }

    public Vacunas save(VacunaRequestDTO dto) {
        Vacunas vacuna = new Vacunas();
        aplicarDatos(vacuna, dto);
        return vacunasRepository.save(vacuna);
    }

    public Vacunas update(Vacunas vacunaExistente, VacunaRequestDTO dto) {
        aplicarDatos(vacunaExistente, dto);
        return vacunasRepository.save(vacunaExistente);
    }

    public void delete(Long id) {
        if (!vacunasRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacuna no encontrada");
        }
        vacunasRepository.deleteById(id);
    }

    private void aplicarDatos(Vacunas vacuna, VacunaRequestDTO dto) {
        vacuna.setMascotaId(dto.getMascotaId());
        vacuna.setNombreVacuna(dto.getNombreVacuna());
        vacuna.setMarca(dto.getMarca());
        vacuna.setNumeroDosis(dto.getNumeroDosis());
        vacuna.setFechaAplicacion(dto.getFechaAplicacion());
        vacuna.setFechaProximoRefuerzo(dto.getFechaProximoRefuerzo());
        vacuna.setVeterinarioAplicador(dto.getVeterinarioAplicador());
        vacuna.setObservaciones(dto.getObservaciones());
    }
}
