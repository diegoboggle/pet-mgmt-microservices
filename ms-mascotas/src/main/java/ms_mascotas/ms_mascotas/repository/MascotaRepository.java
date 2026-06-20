package ms_mascotas.ms_mascotas.repository;

import java.util.List;
import ms_mascotas.ms_mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByUsuarioId(Long usuarioId);
}
