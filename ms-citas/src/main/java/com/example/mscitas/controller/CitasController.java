package com.example.mscitas.controller;

import com.example.mscitas.dto.CitaRequestDTO;
import com.example.mscitas.model.Citas;
import com.example.mscitas.service.CitasService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/citas")
public class CitasController {

    private final CitasService citasService;

    public CitasController(CitasService citasService) {
        this.citasService = citasService;
    }

    @GetMapping
    public ResponseEntity<List<Citas>> listar() {
        List<Citas> citas = citasService.findAll();
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Citas> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(citasService.findById(id));
    }

    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<Citas>> listarPorMascota(@PathVariable Long mascotaId) {
        List<Citas> citas = citasService.findByMascotaId(mascotaId);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Citas>> listarPorEstado(@PathVariable String estado) {
        List<Citas> citas = citasService.findByEstado(estado);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/veterinaria/{veterinariaId}")
    public ResponseEntity<List<Citas>> listarPorVeterinaria(@PathVariable Long veterinariaId) {
        List<Citas> citas = citasService.findByVeterinariaId(veterinariaId);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @PostMapping
    public ResponseEntity<Citas> guardar(@Valid @RequestBody CitaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(citasService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Citas> actualizar(@PathVariable Long id, @Valid @RequestBody CitaRequestDTO dto) {
        Citas existente = citasService.findById(id);
        return ResponseEntity.ok(citasService.update(existente, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Citas> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        Citas existente = citasService.findById(id);
        return ResponseEntity.ok(citasService.cambiarEstado(existente, nuevoEstado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
