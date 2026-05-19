package com.example.msvacunas.repository;

import com.example.msvacunas.model.Vacunas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacunasRepository extends JpaRepository<Vacunas, Long>{

    List<Vacunas> findByMascotaId(Long mascotaId);
}
