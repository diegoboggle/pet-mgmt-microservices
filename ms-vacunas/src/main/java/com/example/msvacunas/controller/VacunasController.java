package com.example.msvacunas.controller;

import com.example.msvacunas.model.Vacunas;
import com.example.msvacunas.service.VacunasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacunas")
public class VacunasController {
    
    @Autowired
    private VacunasService vacunasService;

    // GET /api/v1/vacunas — Lista todas las vacunas
    @GetMapping
    public ResponseEntity<List<Vacunas>> listar(){
        List<Vacunas> vacunas = vacunasService.findAll();
        if (vacunas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vacunas);
    }

    // GET /api/v1/vacunas/{id} — Busca una vacuna por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vacunas> buscar(@PathVariable Long id){
        try {
            Vacunas vacuna = vacunasService.findById(id);
            return ResponseEntity.ok(vacuna);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
    }
}
    
    // GET /api/v1/vacunas/mascota/{mascotaId} — Lista todas las vacunas de una mascota
    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<Vacunas>> listarPorMascota(@PathVariable Long mascotaId){
        List<Vacunas> vacunas = vacunasService.findByMascotaId(mascotaId);
        if (vacunas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vacunas);
    }

    // POST /api/v1/vacunas — Registra una vacuna nueva
    @PostMapping 
    public ResponseEntity<Vacunas> guardar(@RequestBody Vacunas vacuna){
        Vacunas vacunaGuardada = vacunasService.save(vacuna);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacunaGuardada);
    }

    // PUT /api/v1/vacunas/{id} — Actualiza una vacuna existente
    @PutMapping("/{id}")
    public ResponseEntity<Vacunas> actualizar(@PathVariable Long id, @RequestBody Vacunas vacuna){
        try {
            Vacunas vacunaExistente = vacunasService.findById(id);
            vacunaExistente.setMascotaId(vacuna.getMascotaId());
            vacunaExistente.setNombreVacuna(vacuna.getNombreVacuna());
            vacunaExistente.setMarca(vacuna.getMarca());
            vacunaExistente.setNumeroDosis(vacuna.getNumeroDosis());
            vacunaExistente.setFechaAplicacion(vacuna.getFechaAplicacion());
            vacunaExistente.setFechaProximoRefuerzo(vacuna.getFechaProximoRefuerzo());
            vacunaExistente.setVeterinarioAplicador(vacuna.getVeterinarioAplicador());
            vacunaExistente.setObservaciones(vacuna.getObservaciones());

            vacunasService.save(vacunaExistente);
            return ResponseEntity.ok(vacunaExistente);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/v1/vacunas/{id} — Elimina una vacuna por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            vacunasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
