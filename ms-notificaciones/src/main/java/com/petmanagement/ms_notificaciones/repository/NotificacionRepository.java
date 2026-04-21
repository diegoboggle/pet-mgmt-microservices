package com.petmanagement.ms_notificaciones.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petmanagement.ms_notificaciones.model.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByDestinatario(String destinatario);

    List<Notificacion> findByEnviada(boolean enviada);
}