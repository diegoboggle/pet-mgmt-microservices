package ms_usuario.usuario.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")

public class Usuario {

    @Id // identificador único.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental.
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío.") //validacion nombre.
    @Column(nullable = false) // validacion base de datos valores nulos.
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío.") //validacion apellido.
    @Column(nullable = false) // validacion base de datos valores nulos.
    private String apellido;

    @Email(message = "El correo debe ser válido.") //validacion email.
    @NotBlank(message = "El correo no puede estar vacío.") //validacion email.
    @Column(nullable = false, unique = true) // validacion base de datos valores únicos y nulos.
    private String email;

    @Size(min = 9, max = 15, message = "El teléfono debe contener entre 9 y 15 caracteres.") //validacion telefono.
    private String telefono;

    private String direccion;

}
