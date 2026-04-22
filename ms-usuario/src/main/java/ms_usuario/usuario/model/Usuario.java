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

    @Column(nullable = false) // validacion base de datos valores nulos.
    private String nombre;

    @Column(nullable = false) // validacion base de datos valores nulos.
    private String apellido;

    @Column(nullable = false, unique = true) // validacion base de datos valores únicos y nulos.
    private String email;

    @Column(length = 15)
    private String telefono;

    private String direccion;


    //ms-auth necesitará de estos datos

    @Column(nullable = false)
    private String password; 

    @Column(nullable = false)
    private String rol;

}
