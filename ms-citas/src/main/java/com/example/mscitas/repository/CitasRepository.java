package com.example.mscitas.repository;

import com.example.mscitas.model.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Long>{

    //SELECT * FROM cita WHERE mascota_id = ?
    // Trae todas las citas de una mascota especifica
    List<Citas> findByMascotaId(Long mascotaId);

    //SELECT * FROM cita WHERE estado = ?
    // Util para listar citas PENDIENTES, CONFIRMADAS, etc.
    List<Citas> findByEstado(String estado);

    //SELECT * FROM cita WHERE veterinaria_id = ?
    // Trae todas las citas asignadas a una veterinaria especifica
    List<Citas> findByVeterinariaId(Long veterinariaId);
}
