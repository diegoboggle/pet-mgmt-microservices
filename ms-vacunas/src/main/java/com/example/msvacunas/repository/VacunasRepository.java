package main.java.com.example.msvacunas.repository;

import com.example.msvacunas.model.Vacunas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.repository;

import java.util.List;

@Repository
public interface VacunasRepository extends JpaRepository<Vacunas, Long>{

    //Buscamos todas las vacunas de una mascota especifica por ID
    //Spring genera el sql automaticamente leyendo el nombre del metodo:
    // SELECT * FROM vacunas WHERE mascota_id = ?
    List<Vacunas> findByMascotaId(Long mascotaId);
}
