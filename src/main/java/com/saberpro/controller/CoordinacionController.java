package com.saberpro.controller;

import com.saberpro.model.*;
import com.saberpro.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/coordinacion")
public class CoordinacionController {

    @Autowired private AlumnoService alumnoService;
    @Autowired private FacultadService facultadService;
    @Autowired private ResultadoService resultadoService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalAlumnos", alumnoService.listarTodos().size());
        model.addAttribute("totalAprobados", alumnoService.listarAprobados().size());
        model.addAttribute("totalBeneficios", resultadoService.listarConBeneficio().size());
        return "coordinacion/dashboard";
    }

    // ==================== ALUMNOS CRUD ====================
    @GetMapping("/alumnos")
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.listarTodos());
        return "coordinacion/alumnos/lista";
    }

    @GetMapping("/alumnos/nuevo")
    public String nuevoAlumnoForm(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("facultades", facultadService.listarTodas());
        return "coordinacion/alumnos/formulario";
    }

    @PostMapping("/alumnos/guardar")
    public String guardarAlumno(@ModelAttribute Alumno alumno, RedirectAttributes ra) {
        alumnoService.guardar(alumno);
        ra.addFlashAttribute("mensaje", "Alumno guardado correctamente");
        return "redirect:/coordinacion/alumnos";
    }

    @GetMapping("/alumnos/editar/{id}")
    public String editarAlumno(@PathVariable Long id, Model model) {
        alumnoService.buscarPorId(id).ifPresent(a -> model.addAttribute("alumno", a));
        model.addAttribute("facultades", facultadService.listarTodas());
        return "coordinacion/alumnos/formulario";
    }

    @GetMapping("/alumnos/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id, RedirectAttributes ra) {
        alumnoService.eliminar(id);
        ra.addFlashAttribute("mensaje", "Alumno eliminado");
        return "redirect:/coordinacion/alumnos";
    }

    @GetMapping("/alumnos/aprobar/{id}")
    public String aprobarAlumno(@PathVariable Long id, RedirectAttributes ra) {
        alumnoService.aprobarSaberPro(id);
        ra.addFlashAttribute("mensaje", "Alumno aprobado para Saber Pro");
        return "redirect:/coordinacion/alumnos";
    }

    // ==================== INFORMES ====================
    @GetMapping("/informe/total")
    public String informeTotal(Model model) {
        model.addAttribute("resultados", resultadoService.listarPorTipo("TOTAL"));
        model.addAttribute("tipo", "TOTAL");
        return "coordinacion/informe";
    }

    @GetMapping("/informe/unico")
    public String informeUnico(Model model) {
        model.addAttribute("resultados", resultadoService.listarPorTipo("UNICO"));
        model.addAttribute("tipo", "UNICO");
        return "coordinacion/informe";
    }

    @GetMapping("/informe/beneficios")
    public String informeBeneficios(Model model) {
        model.addAttribute("resultados", resultadoService.listarConBeneficio());
        return "coordinacion/beneficios";
    }
}
