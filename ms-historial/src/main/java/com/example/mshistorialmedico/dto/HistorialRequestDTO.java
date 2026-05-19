package com.example.mshistorialmedico.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
 
// DTO para recibir los datos de una consulta médica desde el cliente
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialRequestDTO {
    private Long mascotaId;
    private LocalDate fechaConsulta;
    private String motivoConsulta;
    private String diagnostico;
    private String sintomas;
    private String tratamiento;
    private String medicamentosRecetados;
    private String veterinarioResponsable;
    private String observaciones;
}