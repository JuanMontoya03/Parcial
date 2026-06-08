package com.saberpro.repository;

import com.saberpro.model.Docente;
import com.saberpro.model.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findByCedula(String cedula);
    List<Docente> findByFacultad(Facultad facultad);
    List<Docente> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
}
