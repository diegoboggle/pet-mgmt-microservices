package com.example.mshistorialmedico.service;

import com.example.mshistorialmedico.model.HistorialMedico;
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

    //Elimina un registro por su ID
    public void delete(Long id){
        historialMedicoRepository.deleteById(id);
    }
}
