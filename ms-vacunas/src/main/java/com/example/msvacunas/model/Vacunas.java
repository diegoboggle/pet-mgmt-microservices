package com.example.msvacunas.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "vacunas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacunas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long mascotaId;
    
    @Column(nullable = false)
    private String nombreVacuna;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private Integer numeroDosis;

    @Column(nullable = false)
    private LocalDate fechaAplicacion;

    @Column(nullable = true)
    private LocalDate fechaProximoRefuerzo;

    @Column(nullable = false)
    private String veterinarioAplicador;

    @Column(nullable = true)
    private String observaciones;

}
