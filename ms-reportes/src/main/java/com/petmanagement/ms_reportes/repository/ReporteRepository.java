package com.petmanagement.ms_reportes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmanagement.ms_reportes.model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findByTipoReporte(String tipoReporte);

    List<Reporte> findBySolicitante(String solicitante);
}