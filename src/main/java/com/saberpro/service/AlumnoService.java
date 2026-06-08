package com.saberpro.service;

import com.saberpro.model.Alumno;
import com.saberpro.model.Facultad;
import com.saberpro.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> listarTodos() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    public Optional<Alumno> buscarPorCedula(String cedula) {
        return alumnoRepository.findByCedula(cedula);
    }

    public List<Alumno> listarPorFacultad(Facultad facultad) {
        return alumnoRepository.findByFacultad(facultad);
    }

    public List<Alumno> listarAprobados() {
        return alumnoRepository.findByAprobadoSaberPro(true);
    }

    public Alumno guardar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public void eliminar(Long id) {
        alumnoRepository.deleteById(id);
    }

    public void aprobarSaberPro(Long id) {
        alumnoRepository.findById(id).ifPresent(alumno -> {
            alumno.setAprobadoSaberPro(true);
            alumnoRepository.save(alumno);
        });
    }

    public List<Alumno> buscarPorNombre(String nombre) {
        return alumnoRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombre, nombre);
    }
}
