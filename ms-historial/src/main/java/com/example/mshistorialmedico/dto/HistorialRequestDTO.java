package com.example.mshistorialmedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialRequestDTO {
    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long mascotaId;

    @NotNull(message = "La fecha de consulta es obligatoria")
    private LocalDate fechaConsulta;

    @NotBlank(message = "El motivo de consulta es obligatorio")
    private String motivoConsulta;

    @NotBlank(message = "El diagnostico es obligatorio")
    private String diagnostico;

    private String sintomas;
    private String tratamiento;
    private String medicamentosRecetados;

    @NotBlank(message = "El veterinario responsable es obligatorio")
    private String veterinarioResponsable;

    private String observaciones;
}
