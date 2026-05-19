package com.example.msvacunas.controller;

import com.example.msvacunas.dto.VacunaRequestDTO;
import com.example.msvacunas.model.Vacunas;
import com.example.msvacunas.service.VacunasService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vacunas")
public class VacunasController {

    private final VacunasService vacunasService;

    public VacunasController(VacunasService vacunasService) {
        this.vacunasService = vacunasService;
    }

    @GetMapping
    public ResponseEntity<List<Vacunas>> listar() {
        List<Vacunas> vacunas = vacunasService.findAll();
        if (vacunas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vacunas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacunas> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(vacunasService.findById(id));
    }

    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<Vacunas>> listarPorMascota(@PathVariable Long mascotaId) {
        List<Vacunas> vacunas = vacunasService.findByMascotaId(mascotaId);
        if (vacunas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vacunas);
    }

    @PostMapping
    public ResponseEntity<Vacunas> guardar(@Valid @RequestBody VacunaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacunasService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacunas> actualizar(@PathVariable Long id, @Valid @RequestBody VacunaRequestDTO dto) {
        Vacunas vacunaExistente = vacunasService.findById(id);
        return ResponseEntity.ok(vacunasService.update(vacunaExistente, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vacunasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
