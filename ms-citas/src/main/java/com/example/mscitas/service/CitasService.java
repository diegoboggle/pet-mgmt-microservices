package com.example.mscitas.service;

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

    //retorna todas las citas registradas
    public List<Citas> findAll(){
        return citasRepository.findAll();
    }

    //retorna una cita por su id
    public Citas findById(Long id){
        return citasRepository.findById(id).get();
    }

    //retorna todas las citas de una mascota en especifico
    public List<Citas> findByMascotaId(Long mascotaId){
        return citasRepository.findByMascotaId(mascotaId);
    }

    //retorna todas las citas filtradas por estado (PENDIENTE, CONFIRMADA, etc.)
    public List<Citas> findByEstado(String estado){
        return citasRepository.findByEstado(estado);
    }

    //retorna todas las citas dee una veterinaria especifica
    public List<Citas> findByVeterinariaId(Long veterinariaId){
        return citasRepository.findByVeterinariaId(veterinariaId);
    }

    //guarda una cita nueva o actualiza una existente}
    public Citas save(Citas citas){
        return citasRepository.save(citas);
    }

    //elimina una cita por su id
    public void delete(Long id){
        citasRepository.deleteById(id);
    }
}
