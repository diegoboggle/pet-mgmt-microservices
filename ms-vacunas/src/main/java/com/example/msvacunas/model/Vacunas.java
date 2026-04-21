package com.example.msvacunas.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "vacunas")
@Data
@NoArgsConstructor
@allArgsConstructor
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
    private int numeroDosis;

    @Column(nullable = false)
    private LocalDate fechaAplicacion;

    @Column(nullable = true)
    private LocalDate fechaProximoRefuerzo;

    @Column(nullable = false)
    private String veterinarioAplicador;

    @Column(nullable = true)
    private String observaciones;

}
