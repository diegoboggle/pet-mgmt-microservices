package com.example.mscitas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaRequestDTO {
    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long mascotaId;

    @NotNull(message = "El ID de la veterinaria es obligatorio")
    private Long veterinariaId;

    @NotNull(message = "La fecha de la cita es obligatoria")
    private LocalDate fechaCita;

    @NotNull(message = "La hora de la cita es obligatoria")
    private LocalTime horaCita;

    @NotBlank(message = "El motivo de la cita es obligatorio")
    private String motivoCita;

    private String observaciones;
}
