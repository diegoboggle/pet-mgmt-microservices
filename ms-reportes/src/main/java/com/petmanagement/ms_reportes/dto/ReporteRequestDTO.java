package com.petmanagement.ms_reportes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteRequestDTO {

    @NotBlank(message = "El tipo de reporte es obligatorio")
    @Size(max = 120, message = "El tipo de reporte no puede superar los 120 caracteres")
    private String tipoReporte;

    @NotBlank(message = "El solicitante es obligatorio")
    @Size(max = 150, message = "El solicitante no puede superar los 150 caracteres")
    private String solicitante;

    @NotBlank(message = "El contenido del reporte es obligatorio")
    @Size(max = 4000, message = "El contenido del reporte no puede superar los 4000 caracteres")
    private String contenido;
}
