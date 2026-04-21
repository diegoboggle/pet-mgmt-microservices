package com.petmanagement.ms_reportes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteRequestDTO {

    @NotBlank(message = "El tipo de reporte es obligatorio")
    private String tipoReporte;

    @NotBlank(message = "El solicitante es obligatorio")
    private String solicitante;

    @NotBlank(message = "El contenido del reporte es obligatorio")
    private String contenido;
}