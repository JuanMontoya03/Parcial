package com.saberpro.repository;

import com.saberpro.model.ResultadoSaberPro;
import com.saberpro.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultadoSaberProRepository extends JpaRepository<ResultadoSaberPro, Long> {
    List<ResultadoSaberPro> findByAlumno(Alumno alumno);
    List<ResultadoSaberPro> findByTipoResultado(String tipoResultado);
    List<ResultadoSaberPro> findByAlumno_Cedula(String cedula);
    List<ResultadoSaberPro> findByTieneBeneficio(boolean tieneBeneficio);
    List<ResultadoSaberPro> findByAlumno_Facultad_Id(Long facultadId);
}
