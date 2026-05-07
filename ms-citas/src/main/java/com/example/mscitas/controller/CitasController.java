package com.example.mscitas.controller;
 
import com.example.mscitas.dto.CitaRequestDTO;
import com.example.mscitas.model.Citas;
import com.example.mscitas.service.CitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
@RestController
@RequestMapping("/api/v1/citas")
public class CitasController {
 
    @Autowired
    private CitasService citasService;
 
    // GET /api/v1/citas — Lista todas las citas
    @GetMapping
    public ResponseEntity<List<Citas>> listar() {
        List<Citas> citas = citasService.findAll();
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }
 
    // GET /api/v1/citas/{id} — Busca una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Citas> buscar(@PathVariable Long id) {
        try {
            Citas cita = citasService.findById(id);
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
 
    // GET /api/v1/citas/mascota/{mascotaId} — Todas las citas de una mascota
    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<Citas>> listarPorMascota(@PathVariable Long mascotaId) {
        List<Citas> citas = citasService.findByMascotaId(mascotaId);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }
 
    // GET /api/v1/citas/estado/{estado} — Citas filtradas por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Citas>> listarPorEstado(@PathVariable String estado) {
        List<Citas> citas = citasService.findByEstado(estado);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }
 
    // GET /api/v1/citas/veterinaria/{veterinariaId} — Citas de una veterinaria
    @GetMapping("/veterinaria/{veterinariaId}")
    public ResponseEntity<List<Citas>> listarPorVeterinaria(@PathVariable Long veterinariaId) {
        List<Citas> citas = citasService.findByVeterinariaId(veterinariaId);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }
 
    // POST /api/v1/citas — Agenda una cita nueva
    // Recibe CitaRequestDTO — el estado lo asigna el Service automáticamente como PENDIENTE
    @PostMapping
    public ResponseEntity<Citas> guardar(@RequestBody CitaRequestDTO dto) {
        Citas nueva = citasService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }
 
    // PUT /api/v1/citas/{id} — Actualiza o reprograma una cita
    @PutMapping("/{id}")
    public ResponseEntity<Citas> actualizar(@PathVariable Long id, @RequestBody CitaRequestDTO dto) {
        try {
            Citas existente = citasService.findById(id);
            Citas actualizada = citasService.update(existente, dto);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
 
    // PATCH /api/v1/citas/{id}/estado?nuevoEstado=CONFIRMADA — Cambia solo el estado
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Citas> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        try {
            Citas existente = citasService.findById(id);
            Citas actualizada = citasService.cambiarEstado(existente, nuevoEstado);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
 
    // DELETE /api/v1/citas/{id} — Elimina una cita por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            citasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}