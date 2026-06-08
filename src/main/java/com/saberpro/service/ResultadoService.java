package com.saberpro.service;

import com.saberpro.model.Alumno;
import com.saberpro.model.ResultadoSaberPro;
import com.saberpro.repository.ResultadoSaberProRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoSaberProRepository resultadoRepository;

    public List<ResultadoSaberPro> listarTodos() {
        return resultadoRepository.findAll();
    }

    public Optional<ResultadoSaberPro> buscarPorId(Long id) {
        return resultadoRepository.findById(id);
    }

    public List<ResultadoSaberPro> listarPorAlumno(Alumno alumno) {
        return resultadoRepository.findByAlumno(alumno);
    }

    public List<ResultadoSaberPro> listarPorCedula(String cedula) {
        return resultadoRepository.findByAlumno_Cedula(cedula);
    }

    public List<ResultadoSaberPro> listarPorTipo(String tipo) {
        return resultadoRepository.findByTipoResultado(tipo);
    }

    public List<ResultadoSaberPro> listarConBeneficio() {
        return resultadoRepository.findByTieneBeneficio(true);
    }

    public List<ResultadoSaberPro> listarPorFacultad(Long facultadId) {
        return resultadoRepository.findByAlumno_Facultad_Id(facultadId);
    }

    public ResultadoSaberPro guardar(ResultadoSaberPro resultado) {
        return resultadoRepository.save(resultado);
    }

    public void eliminar(Long id) {
        resultadoRepository.deleteById(id);
    }
}
