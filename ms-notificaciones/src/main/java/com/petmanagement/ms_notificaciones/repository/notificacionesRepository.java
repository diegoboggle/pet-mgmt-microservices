package com.petmanagement.ms_notificaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmanagement.ms_notificaciones.model.notificaciones;

@Repository
public interface notificacionesRepository extends JpaRepository<notificaciones, Long> {

    List<notificaciones> findByDestinatario(String destinatario);

    List<notificaciones> findByEnviada(boolean enviada);
}
