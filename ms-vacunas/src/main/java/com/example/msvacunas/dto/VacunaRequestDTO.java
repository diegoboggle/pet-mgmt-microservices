package com.example.msvacunas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacunaRequestDTO {
    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long mascotaId;

    @NotBlank(message = "El nombre de la vacuna es obligatorio")
    private String nombreVacuna;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotNull(message = "El numero de dosis es obligatorio")
    @Positive(message = "El numero de dosis debe ser mayor que cero")
    private Integer numeroDosis;

    @NotNull(message = "La fecha de aplicacion es obligatoria")
    private LocalDate fechaAplicacion;

    private LocalDate fechaProximoRefuerzo;

    @NotBlank(message = "El veterinario aplicador es obligatorio")
    private String veterinarioAplicador;

    private String observaciones;
}
