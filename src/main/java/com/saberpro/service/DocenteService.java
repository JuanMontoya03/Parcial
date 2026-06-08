package com.saberpro.service;

import com.saberpro.model.Docente;
import com.saberpro.model.Facultad;
import com.saberpro.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    public List<Docente> listarTodos() {
        return docenteRepository.findAll();
    }

    public Optional<Docente> buscarPorId(Long id) {
        return docenteRepository.findById(id);
    }

    public Optional<Docente> buscarPorCedula(String cedula) {
        return docenteRepository.findByCedula(cedula);
    }

    public List<Docente> listarPorFacultad(Facultad facultad) {
        return docenteRepository.findByFacultad(facultad);
    }

    public Docente guardar(Docente docente) {
        return docenteRepository.save(docente);
    }

    public void eliminar(Long id) {
        docenteRepository.deleteById(id);
    }

    public List<Docente> buscarPorNombre(String nombre) {
        return docenteRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombre, nombre);
    }
}
