package com.example.msvacunas.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
 
// DTO (Data Transfer Object) = clase que recibe los datos que envía el cliente (Postman)
// Sirve para NO exponer la entidad JPA directamente al exterior
// La entidad Vacunas es lo que se guarda en la BD
// El DTO es lo que el cliente envía en el body del POST/PUT
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacunaRequestDTO {
    private Long mascotaId;
    private String nombreVacuna;
    private String marca;
    private Integer numeroDosis;
    private LocalDate fechaAplicacion;
    private LocalDate fechaProximoRefuerzo;
    private String veterinarioAplicador;
    private String observaciones;
}
 