package ms_mascotas.ms_mascotas.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

import ms_mascotas.ms_mascotas.dto.MascotaDTO;
import ms_mascotas.ms_mascotas.model.Mascota;
import ms_mascotas.ms_mascotas.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping
    public ResponseEntity<Mascota> crear(@Valid @RequestBody MascotaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.registrarMascota(dto));
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> listar() {
        return ResponseEntity.ok(mascotaService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Mascota> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Mascota>> listarPorUsuarioId(@PathVariable ("usuarioId") Long usuarioId) {
        List<Mascota> mascotas = mascotaService.listarPorUsuarioId(usuarioId);
        return ResponseEntity.ok(mascotas);
    }
    @GetMapping("/{id}/detalle-completo")
    public ResponseEntity<Map<String, Object>> obtenerDetalleCompleto(@PathVariable Long id) {
    return ResponseEntity.ok(mascotaService.obtenerMascotaConDuenio(id));
}
}   
