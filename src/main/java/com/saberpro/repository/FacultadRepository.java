package com.saberpro.repository;

import com.saberpro.model.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacultadRepository extends JpaRepository<Facultad, Long> {
    List<Facultad> findByNombreContainingIgnoreCase(String nombre);
}
