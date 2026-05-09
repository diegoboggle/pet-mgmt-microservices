package ms_veterinaria.ms_veterinaria.service;

import lombok.extern.slf4j.Slf4j;
import ms_veterinaria.ms_veterinaria.dto.VeterinariaDTO;
import ms_veterinaria.ms_veterinaria.model.Veterinaria;
import ms_veterinaria.ms_veterinaria.repository.VeterinariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VeterinariaService {

    private final VeterinariaRepository veterinarioRepository;

    public VeterinariaService(VeterinariaRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public Veterinaria registrarVeterinario(VeterinariaDTO dto) {
        log.info("Iniciando registro de veterinario: {} {}", dto.getNombre(), dto.getApellido());

        if (veterinarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            log.warn("Registro fallido: El email {} ya existe", dto.getEmail());
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Veterinaria vet = new Veterinaria();
        vet.setNombre(dto.getNombre());
        vet.setApellido(dto.getApellido());
        vet.setEspecialidad(dto.getEspecialidad());
        vet.setEmail(dto.getEmail());
        vet.setTelefono(dto.getTelefono());

        Veterinaria guardado = veterinarioRepository.save(vet);
        log.info("Veterinario registrado exitosamente con ID: {}", guardado.getId());
        return guardado;
    }

    public List<Veterinaria> listarVeterinarios() {
        log.info("Consultando el directorio de veterinarios");
        return veterinarioRepository.findAll();
    }
}