package ms_mascotas.ms_mascotas.controller;

import ms_mascotas.ms_mascotas.dto.MascotaDTO;
import ms_mascotas.ms_mascotas.model.Mascota;
import ms_mascotas.ms_mascotas.repository.MascotaRepository;
import ms_mascotas.ms_mascotas.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    private final MascotaRepository mascotaRepository;
    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService, MascotaRepository mascotaRepository) {
        this.mascotaService = mascotaService;
        this.mascotaRepository = mascotaRepository;
    }

    @PostMapping
    public ResponseEntity<Mascota> crear(@Valid @RequestBody MascotaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.registrarMascota(dto));
    }

    @GetMapping
    public List<Mascota> listar() {
        return mascotaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> buscarPorId(@PathVariable Long id){
        return mascotaRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }    
}