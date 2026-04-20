package com.petmanagement.ms_notificaciones.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacionRequestDTO {

    @NotBlank(message = "El destinatario es obligatorio y no puede estar vacío")
    private String destinatario;

    @NotBlank(message = "El mensaje es obligatorio y no puede estar vacío")
    private String mensaje;

    @NotBlank(message = "El tipo es obligatorio y no puede estar vacío")
    private String tipo;
}