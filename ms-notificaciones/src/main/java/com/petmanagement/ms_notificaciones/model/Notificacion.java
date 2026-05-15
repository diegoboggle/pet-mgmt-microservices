package com.petmanagement.ms_notificaciones.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String destinatario;

    @Column(nullable = false, length = 1000)
    private String mensaje;

    @Column(nullable = false, length = 80)
    private String tipo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaEnvio;

    @Column(nullable = false)
    private boolean enviada;
}
