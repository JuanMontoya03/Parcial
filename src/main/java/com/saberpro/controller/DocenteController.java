package com.saberpro.controller;

import com.saberpro.model.Facultad;
import com.saberpro.service.AlumnoService;
import com.saberpro.service.DocenteService;
import com.saberpro.service.FacultadService;
import com.saberpro.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/docente")
public class DocenteController {

    @Autowired private AlumnoService alumnoService;
    @Autowired private FacultadService facultadService;
    @Autowired private ResultadoService resultadoService;
    @Autowired private DocenteService docenteService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalAlumnos", alumnoService.listarTodos().size());
        return "docente/dashboard";
    }

    @GetMapping("/alumnos/facultad")
    public String alumnosPorFacultad(@RequestParam(required = false) Long facultadId, Model model) {
        model.addAttribute("facultades", facultadService.listarTodas());
        if (facultadId != null) {
            facultadService.buscarPorId(facultadId).ifPresent(f -> {
                model.addAttribute("alumnos", alumnoService.listarPorFacultad(f));
                model.addAttribute("facultadSeleccionada", f);
            });
        }
        return "docente/alumnos-facultad";
    }

    @GetMapping("/alumnos/cedula")
    public String alumnosPorCedula(@RequestParam(required = false) String cedula, Model model) {
        if (cedula != null && !cedula.isBlank()) {
            alumnoService.buscarPorCedula(cedula).ifPresent(a -> model.addAttribute("alumno", a));
            model.addAttribute("cedula", cedula);
        }
        return "docente/alumno-cedula";
    }

    @GetMapping("/informe/total")
    public String informeTotal(Model model) {
        model.addAttribute("resultados", resultadoService.listarPorTipo("TOTAL"));
        model.addAttribute("tipo", "TOTAL");
        return "docente/informe";
    }

    @GetMapping("/informe/unico")
    public String informeUnico(Model model) {
        model.addAttribute("resultados", resultadoService.listarPorTipo("UNICO"));
        model.addAttribute("tipo", "UNICO");
        return "docente/informe";
    }

    @GetMapping("/informe/beneficios")
    public String informeBeneficios(Model model) {
        model.addAttribute("resultados", resultadoService.listarConBeneficio());
        return "docente/beneficios";
    }
}
