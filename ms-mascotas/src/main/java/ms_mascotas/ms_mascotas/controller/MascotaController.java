package ms_mascotas.ms_mascotas.controller;

import jakarta.validation.Valid;
import java.util.List;

import ms_mascotas.ms_mascotas.dto.MascotaDTO;
import ms_mascotas.ms_mascotas.model.Mascota;
import ms_mascotas.ms_mascotas.service.MascotaService;

// 1. Imports correctos de HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

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
    @Operation(summary = "Obtener una mascota por ID con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Mascota>> buscarPorId(@PathVariable Long id) {
        Mascota mascota = mascotaService.buscarPorId(id);
        EntityModel<Mascota> recurso = EntityModel.of(mascota);
        
        // Letra 'O' mayúscula en methodOn, no un cero
        Link selfLink = linkTo(methodOn(MascotaController.class).buscarPorId(id)).withSelfRel();
        recurso.add(selfLink);
        
        Link deleteLink = linkTo(methodOn(MascotaController.class).eliminarMascota(id)).withRel("eliminar");
        recurso.add(deleteLink); // Aquí agregamos el deleteLink correctamente
        
        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Mascota>> listarPorUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
        List<Mascota> mascotas = mascotaService.listarPorUsuarioId(usuarioId);
        return ResponseEntity.ok(mascotas);
    }

    @DeleteMapping("/{id}")
    @Operation(summary= "Eliminar una mascota por ID")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id){
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }
}