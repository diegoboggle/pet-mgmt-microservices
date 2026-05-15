package ms_mascotas.ms_mascotas.service;

import ms_mascotas.ms_mascotas.dto.MascotaDTO;
import ms_mascotas.ms_mascotas.model.Mascota;
import ms_mascotas.ms_mascotas.repository.MascotaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    public Mascota registrarMascota(MascotaDTO dto) {
        Mascota mascota = new Mascota();
        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        mascota.setRaza(dto.getRaza());
        mascota.setEdad(dto.getEdad());
        mascota.setUsuarioId(dto.getUsuarioId());
        
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }
    public List<Mascota> listarMascotaID(Long MascotaID){
        return mascotaRepository.findByUsuarioId(MascotaID);
    }
    
}