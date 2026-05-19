package com.example.mshistorialmedico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "historial_medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ID de la mascota no usamos manyToOne porque cada ms tiene su propia bdd
    @Column(nullable = false)
    private Long mascotaId;

    @Column(nullable = false)
    private LocalDate fechaConsulta;

    @Column(nullable = false)
    private String motivoConsulta;
    
    @Column(nullable = false)
    private String diagnostico;

    @Column(nullable = true)
    private String sintomas;

    @Column(nullable = true)
    private String tratamiento;

    @Column(nullable = true)
    private String medicamentosRecetados;

    @Column(nullable = false)
    private String veterinarioResponsable;

    @Column(nullable = true)
    private String observaciones;
}
