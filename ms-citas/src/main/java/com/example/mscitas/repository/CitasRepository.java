package com.example.mscitas.repository;

import com.example.mscitas.model.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Long>{

    List<Citas> findByMascotaId(Long mascotaId);

    List<Citas> findByEstado(String estado);

    List<Citas> findByVeterinariaId(Long veterinariaId);
}
