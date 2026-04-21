package com.petmanagement.ms_recordatorios.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petmanagement.ms_recordatorios.model.Recordatorio;

@Repository
public interface RecordatorioRepository extends JpaRepository<Recordatorio, Long> {

    List<Recordatorio> findByDestinatario(String destinatario);

    List<Recordatorio> findByCompletado(boolean completado);
}