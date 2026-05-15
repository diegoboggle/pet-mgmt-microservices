package com.petmanagement.ms_recordatorios.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordatorioRequestDTO {

    @NotBlank(message = "El destinatario es obligatorio y no puede estar vacío")
    @Size(max = 150, message = "El destinatario no puede superar los 150 caracteres")
    private String destinatario;

    @NotBlank(message = "El mensaje es obligatorio y no puede estar vacío")
    @Size(max = 1000, message = "El mensaje no puede superar los 1000 caracteres")
    private String mensaje;

    @NotNull(message = "La fecha del recordatorio es obligatoria")
    @FutureOrPresent(message = "La fecha del recordatorio debe ser actual o futura")
    private LocalDateTime fechaRecordatorio;
}
