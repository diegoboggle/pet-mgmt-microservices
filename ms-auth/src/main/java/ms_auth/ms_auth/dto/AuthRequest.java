package ms_auth.ms_auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Objeto que representa una solicitud de autenticación (Login)")
public class AuthRequest {
    
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Formato de email inválido")
    @Schema(description = "Correo electrónico del usuario", example = "usuario@correo.com")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Schema(description = "Contraseña en texto plano provista por el usuario", example = "MiClaveSecreta123")
    private String password;

    @Schema(description = "Rol del usuario, usado internamente", hidden = true)
    private String rol;
}