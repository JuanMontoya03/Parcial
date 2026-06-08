package com.saberpro.repository;

import com.saberpro.model.Alumno;
import com.saberpro.model.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByCedula(String cedula);
    List<Alumno> findByFacultad(Facultad facultad);
    List<Alumno> findByAprobadoSaberPro(boolean aprobado);
    List<Alumno> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
}
