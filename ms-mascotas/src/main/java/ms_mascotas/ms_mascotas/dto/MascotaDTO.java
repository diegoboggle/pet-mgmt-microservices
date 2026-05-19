package ms_mascotas.ms_mascotas.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MascotaDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    private String especie;

    private String raza;

    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer edad;

    @NotNull(message = "El ID del dueño es obligatorio")
    private Long usuarioId;
}