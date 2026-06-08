package com.saberpro.service;

import com.saberpro.model.Director;
import com.saberpro.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public List<Director> listarTodos() {
        return directorRepository.findAll();
    }

    public Optional<Director> buscarPorId(Long id) {
        return directorRepository.findById(id);
    }

    public Optional<Director> buscarPorCedula(String cedula) {
        return directorRepository.findByCedula(cedula);
    }

    public Director guardar(Director director) {
        return directorRepository.save(director);
    }

    public void eliminar(Long id) {
        directorRepository.deleteById(id);
    }

    public List<Director> buscarPorNombre(String nombre) {
        return directorRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombre, nombre);
    }
}
