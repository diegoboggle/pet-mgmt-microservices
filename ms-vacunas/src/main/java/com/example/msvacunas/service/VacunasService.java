package com.example.msvacunas.service;

import com.example.msvacunas.dto.VacunaRequestDTO;
import com.example.msvacunas.model.Vacunas;
import com.example.msvacunas.repository.VacunasRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VacunasService {
    
    @Autowired
    private VacunasRepository vacunasRepository;

    public List<Vacunas> findAll(){
        return vacunasRepository.findAll();
    }

    public Vacunas findById(Long id){
        return vacunasRepository.findById(id).get();
    }

    public List<Vacunas> findByMascotaId(Long mascotaId){
        return vacunasRepository.findByMascotaId(mascotaId);
    }

    public Vacunas save(Vacunas vacuna){
        return vacunasRepository.save(vacuna);
    }

    // Recibe un DTO, lo convierte a entidad y lo guarda
    // Este método se usa tanto para crear (POST) como para actualizar (PUT)
    public Vacunas save(VacunaRequestDTO dto) {
        Vacunas vacuna = new Vacunas();
        vacuna.setMascotaId(dto.getMascotaId());
        vacuna.setNombreVacuna(dto.getNombreVacuna());
        vacuna.setMarca(dto.getMarca());
        vacuna.setNumeroDosis(dto.getNumeroDosis());
        vacuna.setFechaAplicacion(dto.getFechaAplicacion());
        vacuna.setFechaProximoRefuerzo(dto.getFechaProximoRefuerzo());
        vacuna.setVeterinarioAplicador(dto.getVeterinarioAplicador());
        vacuna.setObservaciones(dto.getObservaciones());
        return vacunasRepository.save(vacuna);
    }
 
    // Versión especial del save para el PUT:
    // Recibe la entidad ya existente (con su ID) y le aplica los datos del DTO
    public Vacunas update(Vacunas vacunaExistente, VacunaRequestDTO dto) {
        vacunaExistente.setMascotaId(dto.getMascotaId());
        vacunaExistente.setNombreVacuna(dto.getNombreVacuna());
        vacunaExistente.setMarca(dto.getMarca());
        vacunaExistente.setNumeroDosis(dto.getNumeroDosis());
        vacunaExistente.setFechaAplicacion(dto.getFechaAplicacion());
        vacunaExistente.setFechaProximoRefuerzo(dto.getFechaProximoRefuerzo());
        vacunaExistente.setVeterinarioAplicador(dto.getVeterinarioAplicador());
        vacunaExistente.setObservaciones(dto.getObservaciones());
        return vacunasRepository.save(vacunaExistente);
    }

    public void delete(Long id){
        vacunasRepository.deleteById(id);
    }
}
