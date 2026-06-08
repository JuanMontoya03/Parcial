package com.saberpro.controller;

import com.saberpro.model.*;
import com.saberpro.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private DirectorService directorService;
    @Autowired private DocenteService docenteService;
    @Autowired private FacultadService facultadService;
    @Autowired private ResultadoService resultadoService;

    // ==================== DASHBOARD ====================
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalDirectores", directorService.listarTodos().size());
        model.addAttribute("totalDocentes", docenteService.listarTodos().size());
        model.addAttribute("totalFacultades", facultadService.listarTodas().size());
        return "admin/dashboard";
    }

    // ==================== DIRECTORES ====================
    @GetMapping("/directores")
    public String listarDirectores(Model model) {
        model.addAttribute("directores", directorService.listarTodos());
        return "admin/directores/lista";
    }

    @GetMapping("/directores/nuevo")
    public String nuevoDirectorForm(Model model) {
        model.addAttribute("director", new Director());
        model.addAttribute("facultades", facultadService.listarTodas());
        return "admin/directores/formulario";
    }

    @PostMapping("/directores/guardar")
    public String guardarDirector(@ModelAttribute Director director, RedirectAttributes ra) {
        directorService.guardar(director);
        ra.addFlashAttribute("mensaje", "Director guardado correctamente");
        return "redirect:/admin/directores";
    }

    @GetMapping("/directores/editar/{id}")
    public String editarDirector(@PathVariable Long id, Model model) {
        directorService.buscarPorId(id).ifPresent(d -> model.addAttribute("director", d));
        model.addAttribute("facultades", facultadService.listarTodas());
        return "admin/directores/formulario";
    }

    @GetMapping("/directores/eliminar/{id}")
    public String eliminarDirector(@PathVariable Long id, RedirectAttributes ra) {
        directorService.eliminar(id);
        ra.addFlashAttribute("mensaje", "Director eliminado");
        return "redirect:/admin/directores";
    }

    // ==================== DOCENTES ====================
    @GetMapping("/docentes")
    public String listarDocentes(Model model) {
        model.addAttribute("docentes", docenteService.listarTodos());
        return "admin/docentes/lista";
    }

    @GetMapping("/docentes/nuevo")
    public String nuevoDocenteForm(Model model) {
        model.addAttribute("docente", new Docente());
        model.addAttribute("facultades", facultadService.listarTodas());
        return "admin/docentes/formulario";
    }

    @PostMapping("/docentes/guardar")
    public String guardarDocente(@ModelAttribute Docente docente, RedirectAttributes ra) {
        docenteService.guardar(docente);
        ra.addFlashAttribute("mensaje", "Docente guardado correctamente");
        return "redirect:/admin/docentes";
    }

    @GetMapping("/docentes/editar/{id}")
    public String editarDocente(@PathVariable Long id, Model model) {
        docenteService.buscarPorId(id).ifPresent(d -> model.addAttribute("docente", d));
        model.addAttribute("facultades", facultadService.listarTodas());
        return "admin/docentes/formulario";
    }

    @GetMapping("/docentes/eliminar/{id}")
    public String eliminarDocente(@PathVariable Long id, RedirectAttributes ra) {
        docenteService.eliminar(id);
        ra.addFlashAttribute("mensaje", "Docente eliminado");
        return "redirect:/admin/docentes";
    }

    // ==================== FACULTADES ====================
    @GetMapping("/facultades")
    public String listarFacultades(Model model) {
        model.addAttribute("facultades", facultadService.listarTodas());
        return "admin/facultades/lista";
    }

    @GetMapping("/facultades/nuevo")
    public String nuevaFacultadForm(Model model) {
        model.addAttribute("facultad", new Facultad());
        return "admin/facultades/formulario";
    }

    @PostMapping("/facultades/guardar")
    public String guardarFacultad(@ModelAttribute Facultad facultad, RedirectAttributes ra) {
        facultadService.guardar(facultad);
        ra.addFlashAttribute("mensaje", "Facultad guardada correctamente");
        return "redirect:/admin/facultades";
    }

    @GetMapping("/facultades/editar/{id}")
    public String editarFacultad(@PathVariable Long id, Model model) {
        facultadService.buscarPorId(id).ifPresent(f -> model.addAttribute("facultad", f));
        return "admin/facultades/formulario";
    }

    @GetMapping("/facultades/eliminar/{id}")
    public String eliminarFacultad(@PathVariable Long id, RedirectAttributes ra) {
        facultadService.eliminar(id);
        ra.addFlashAttribute("mensaje", "Facultad eliminada");
        return "redirect:/admin/facultades";
    }

    // ==================== BENEFICIOS ====================
    @GetMapping("/beneficios")
    public String verBeneficios(Model model) {
        model.addAttribute("resultados", resultadoService.listarConBeneficio());
        return "admin/beneficios";
    }
}
