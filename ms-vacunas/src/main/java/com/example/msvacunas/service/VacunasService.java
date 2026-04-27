package com.example.msvacunas.service;

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

    public void delete(Long id){
        vacunasRepository.deleteById(id);
    }
}
