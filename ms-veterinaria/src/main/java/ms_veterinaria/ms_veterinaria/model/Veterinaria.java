package ms_veterinaria.ms_veterinaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "veterinarios")
public class Veterinaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String especialidad; // ej: Cirujano, General, Exóticos

    @Column(nullable = false, unique = true)
    private String email;

    private String telefono;
}
