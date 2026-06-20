package ms_usuario.usuario.dto;


import jakarta.validation.constraints.*;
import lombok.Data;



import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Objeto de Transferencia de Datos para la entidad Usuario")
public class UsuarioDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre del usuario", example = "Juan")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Schema(description = "Apellido del usuario", example = "Pérez")
    private String apellido;

    @Email(message = "Debe ser un email válido")
    @NotBlank(message = "El email es obligatorio")
    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@example.com")
    private String email;

    @Size(max = 15, message = "El teléfono no puede exceder los 15 caracteres")
    @Schema(description = "Número de teléfono", example = "+56912345678")
    private String telefono;

    @Schema(description = "Dirección física del usuario", example = "Av. Libertador 123")
    private String direccion;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Schema(description = "Contraseña en texto plano para registro", example = "MiClaveSecreta123")
    private String password; 

    @NotBlank(message = "El rol es obligatorio")
    @Schema(description = "Rol del usuario en el sistema", example = "CLIENTE")
    private String rol;
}