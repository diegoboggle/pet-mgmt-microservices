package ms_veterinaria.ms_veterinaria.controller;

import jakarta.validation.Valid;
import ms_veterinaria.ms_veterinaria.dto.VeterinariaDTO;
import ms_veterinaria.ms_veterinaria.model.Veterinaria;
import ms_veterinaria.ms_veterinaria.service.VeterinariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/veterinarios")
public class VeterinariaController {

    private final VeterinariaService veterinariaService;

    public VeterinariaController(VeterinariaService veterinariaService) {
        this.veterinariaService = veterinariaService;
    }

    @PostMapping
    public ResponseEntity<Veterinaria> registrar(@Valid @RequestBody VeterinariaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinariaService.registrarVeterinario(dto));
    }

    @GetMapping
    public ResponseEntity<List<Veterinaria>> listar() {
        return ResponseEntity.ok(veterinariaService.listarVeterinarios());
    }
}