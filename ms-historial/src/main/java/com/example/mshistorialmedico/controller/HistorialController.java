package com.example.mshistorialmedico.controller;

import com.example.mshistorialmedico.dto.HistorialRequestDTO;
import com.example.mshistorialmedico.model.HistorialMedico;
import com.example.mshistorialmedico.service.HistorialService;
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
@RequestMapping("/api/v1/historial")
public class HistorialController {

    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping
    public ResponseEntity<List<HistorialMedico>> listar() {
        List<HistorialMedico> historial = historialService.findAll();
        if (historial.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialMedico> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(historialService.findById(id));
    }

    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<HistorialMedico>> listarPorMascota(@PathVariable Long mascotaId) {
        List<HistorialMedico> historial = historialService.findByMascotaId(mascotaId);
        if (historial.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }

    @PostMapping
    public ResponseEntity<HistorialMedico> guardar(@Valid @RequestBody HistorialRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historialService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialMedico> actualizar(@PathVariable Long id, @Valid @RequestBody HistorialRequestDTO dto) {
        HistorialMedico existente = historialService.findById(id);
        return ResponseEntity.ok(historialService.update(existente, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        historialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
