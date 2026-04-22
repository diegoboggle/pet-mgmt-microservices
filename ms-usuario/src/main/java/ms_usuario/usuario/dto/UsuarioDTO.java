package ms_usuario.usuario.model.usuario; // Asegúrate de que el paquete sea el correcto

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio.")
    private String apellido;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "Debe ingresar un formato de correo válido.")
    private String email;

    @Size(min = 9, max = 15, message = "El teléfono debe tener entre 9 y 15 caracteres.")
    private String telefono;

    private String direccion;

    // Estos campos son vitales para cuando hagas el login más adelante
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

    @NotBlank(message = "Debe asignar un rol (ej: ADMIN, VETERINARIO, CLIENTE).")
    private String rol;
}