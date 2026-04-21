package com.petmanagement.ms_recordatorios.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordatorioRequestDTO {

    @NotBlank(message = "El destinatario es obligatorio y no puede estar vacío")
    private String destinatario;

    @NotBlank(message = "El mensaje es obligatorio y no puede estar vacío")
    private String mensaje;

    @NotNull(message = "La fecha del recordatorio es obligatoria")
    private LocalDateTime fechaRecordatorio;
}