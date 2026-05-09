package ms_veterinaria.ms_veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ms_veterinaria.ms_veterinaria.model.Veterinaria;
import java.util.Optional;

public interface VeterinariaRepository extends JpaRepository<Veterinaria, Long> {
    Optional<Veterinaria> findByEmail(String email);


}
