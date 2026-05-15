package com.petmanagement.ms_notificaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NotificacionRequestDTO {

    @NotBlank(message = "El destinatario es obligatorio y no puede estar vacío")
    @Size(max = 150, message = "El destinatario no puede superar los 150 caracteres")
    private String destinatario;

    @NotBlank(message = "El mensaje es obligatorio y no puede estar vacío")
    @Size(max = 1000, message = "El mensaje no puede superar los 1000 caracteres")
    private String mensaje;

    @NotBlank(message = "El tipo es obligatorio y no puede estar vacío")
    @Size(max = 80, message = "El tipo no puede superar los 80 caracteres")
    private String tipo;

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
