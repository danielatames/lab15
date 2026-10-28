package com.fieldtech.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fieldtech.domain.Incidencia;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByTecnicoCodigoOrderByFechaReporteDesc(String codigo);
}