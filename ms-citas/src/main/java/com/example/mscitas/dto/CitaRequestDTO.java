package com.example.mscitas.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
 
// DTO para recibir los datos de una cita desde el cliente
// El campo "estado" NO está aquí intencionalmente:
// El estado siempre parte como "PENDIENTE" — es una regla de negocio definida en el Service
// El cliente no debe poder elegir el estado al crear una cita
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CitaRequestDTO {
    private Long mascotaId;
    private Long veterinariaId;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String motivoCita;
    private String observaciones;
}
 