package com.petmanagement.ms_reportes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
