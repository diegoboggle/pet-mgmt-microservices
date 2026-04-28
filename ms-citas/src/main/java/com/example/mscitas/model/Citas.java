package com.example.mscitas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long mascotaId;

    @Column(nullable = false)
    private Long veterinariaId;

    @Column(nullable = false)
    private LocalDate fechaCita;

    @Column(nullable = false)
    private LocalTime horaCita;

    @Column(nullable = false)
    private String motivoCita;

    //Estado de la cita: PENDIENTE, CONFIRMADA, CANCELADA, COMPLETADA
    // Usamos String simple para no complicar con enum por ahora
    @Column(nullable = false)
    private String estado;

    @Column(nullable = true)
    private String observaciones;
}
