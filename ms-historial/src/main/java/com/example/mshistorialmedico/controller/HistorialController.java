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
}
