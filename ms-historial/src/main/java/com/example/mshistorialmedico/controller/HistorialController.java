package com.example.mshistorialmedico.controller;

import com.example.mshistorialmedico.model.HistorialMedico;
import com.example.mshistorialmedico.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    // GET /api/v1/historial - Lista todos los registros
    @GetMapping
    public ResponseEntity<List<HistorialMedico>> listar(){
        List<HistorialMedico> historial = historialService.findAll();
        if(historial.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }

    // GET /api/v1/historial/{id} — Busca un registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<HistorialMedico> buscar(@PathVariable Long id){
        try {
            HistorialMedico historial = historialService.findById(id);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/v1/historial/mascota/{mascotaId} - todo el historial de una mascota 
    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<HistorialMedico>> listarPorMascota(@PathVariable Long mascotaId){
        List<HistorialMedico> historial = historialService.findByMascotaId(mascotaId);
        if (historial.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }

    // POST /api/v1/historial - registra una nueva consulta medica
    @PostMapping
    public ResponseEntity<HistorialMedico> guardar(@RequestBody HistorialMedico historialMedico){
        HistorialMedico nuevo = historialService.save(historialMedico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo); 
    }

    // PUT /api/v1/historial/{id} - Actualiza un registro existente
    @PutMapping("/{id}")
    public ResponseEntity<HistorialMedico> actualizar(@PathVariable Long id, @RequestBody HistorialMedico historialMedico){
        try {
            HistorialMedico existente = historialService.findById(id);
            existente.setMascotaId(historialMedico.getMascotaId());
            existente.setFechaConsulta(historialMedico.getFechaConsulta());
            existente.setMotivoConsulta(historialMedico.getMotivoConsulta());
            existente.setDiagnostico(historialMedico.getDiagnostico());
            existente.setSintomas(historialMedico.getSintomas());
            existente.setTratamiento(historialMedico.getTratamiento());
            existente.setMedicamentosRecetados(historialMedico.getMedicamentosRecetados());
            existente.setVeterinarioResponsable(historialMedico.getVeterinarioResponsable());
            existente.setObservaciones(historialMedico.getObservaciones());
            historialService.save(existente);
            return ResponseEntity.ok(existente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/v1/historial/{id} - Elimina un registro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            historialService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
