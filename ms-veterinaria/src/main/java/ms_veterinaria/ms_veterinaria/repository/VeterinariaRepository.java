package ms_veterinaria.ms_veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ms_veterinaria.ms_veterinaria.model.Veterinaria;

public interface VeterinariaRepository extends JpaRepository<Veterinaria, Long> {
    boolean existsByEmail(String email);
}
