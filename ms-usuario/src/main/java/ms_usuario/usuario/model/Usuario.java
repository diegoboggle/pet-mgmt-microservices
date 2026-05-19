package ms_usuario.usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // Reemplazamos @Data por Getter y Setter para evitar problemas con Hibernate
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id // Identificador único.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental.
    private Long id;

    @Column(nullable = false) 
    private String nombre;

    @Column(nullable = false) 
    private String apellido; 

    @Column(nullable = false, unique = true) 
    private String email; 

    @Column(length = 15)
    private String telefono;

    private String direccion;

    // ms-auth necesitará de estos datos
    @Column(nullable = false)
    private String password; 

    @Column(nullable = false)
    private String rol;
}