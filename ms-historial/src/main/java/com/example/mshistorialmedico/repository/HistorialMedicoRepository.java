package com.example.mshistorialmedico.repository;

import com.example.mshistorialmedico.model.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long>{
    List<HistorialMedico> findByMascotaId(Long mascotaId);
}
