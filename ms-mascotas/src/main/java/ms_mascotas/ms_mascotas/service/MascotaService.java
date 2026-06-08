package ms_mascotas.ms_mascotas.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ms_mascotas.ms_mascotas.client.UsuarioClient;
import ms_mascotas.ms_mascotas.dto.MascotaDTO;
import ms_mascotas.ms_mascotas.model.Mascota;
import ms_mascotas.ms_mascotas.repository.MascotaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final UsuarioClient usuarioClient;

    public MascotaService(MascotaRepository mascotaRepository, UsuarioClient usuarioClient) {
        this.mascotaRepository = mascotaRepository;
        this.usuarioClient = usuarioClient;
    }

    public Mascota registrarMascota(MascotaDTO dto) {
        if (dto.getUsuarioId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del propietario es obligatorio");
        }

        Mascota mascota = new Mascota();
        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        mascota.setRaza(dto.getRaza());
        mascota.setEdad(dto.getEdad());
        mascota.setUsuarioId(dto.getUsuarioId());
        
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> listarTodos() {
        return mascotaRepository.findAll();
    }

    public List<Mascota> listarPorUsuarioId(long usuarioId) {
        return mascotaRepository.findByUsuarioId(usuarioId);
    }

    public Mascota buscarPorId(long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mascota no encontrada con el ID: " + id));
    }

    public Map<String, Object> obtenerMascotaConDuenio(Long id) {
        // 1. Buscamos la mascota en la BD local
        Mascota mascota = buscarPorId(id);

        // 2. Llamada síncrona al ms-usuario para traer los datos del dueño
        Object duenio = usuarioClient.obtenerUsuarioPorId(mascota.getUsuarioId());

        // 3. Construcción del objeto compuesto
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mascota", mascota);
        respuesta.put("propietario", duenio);

        return respuesta;
}
}
