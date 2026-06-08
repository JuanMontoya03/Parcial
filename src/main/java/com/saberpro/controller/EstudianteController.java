package com.saberpro.controller;

import com.saberpro.model.*;
import com.saberpro.service.*;
import com.saberpro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired private AlumnoService alumnoService;
    @Autowired private ResultadoService resultadoService;
    @Autowired private UsuarioRepository usuarioRepository;

    private Alumno getAlumnoActual(Authentication auth) {
        return usuarioRepository.findByUsername(auth.getName())
                .flatMap(u -> alumnoService.listarTodos().stream()
                        .filter(a -> a.getUsuario() != null && a.getUsuario().getId().equals(u.getId()))
                        .findFirst())
                .orElse(null);
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        Alumno alumno = getAlumnoActual(auth);
        model.addAttribute("alumno", alumno);
        if (alumno != null) {
            model.addAttribute("resultados", resultadoService.listarPorAlumno(alumno));
        }
        return "estudiante/dashboard";
    }

    @GetMapping("/identificacion")
    public String identificacion(Authentication auth, Model model) {
        Alumno alumno = getAlumnoActual(auth);
        model.addAttribute("alumno", alumno);
        return "estudiante/identificacion";
    }

    @GetMapping("/resultados/total")
    public String resultadosTotal(Authentication auth, Model model) {
        Alumno alumno = getAlumnoActual(auth);
        if (alumno != null) {
            model.addAttribute("resultados",
                resultadoService.listarPorAlumno(alumno).stream()
                    .filter(r -> "TOTAL".equals(r.getTipoResultado())).toList());
        }
        model.addAttribute("tipo", "TOTAL");
        return "estudiante/resultados";
    }

    @GetMapping("/resultados/unico")
    public String resultadosUnico(Authentication auth, Model model) {
        Alumno alumno = getAlumnoActual(auth);
        if (alumno != null) {
            model.addAttribute("resultados",
                resultadoService.listarPorAlumno(alumno).stream()
                    .filter(r -> "UNICO".equals(r.getTipoResultado())).toList());
        }
        model.addAttribute("tipo", "UNICO");
        return "estudiante/resultados";
    }

    @GetMapping("/pago")
    public String pagoPorFacultad(Authentication auth, Model model) {
        Alumno alumno = getAlumnoActual(auth);
        model.addAttribute("alumno", alumno);
        return "estudiante/pago";
    }

    @PostMapping("/pago/cargar")
    public String cargarPago(Authentication auth,
                             @RequestParam String comprobante,
                             RedirectAttributes ra) {
        Alumno alumno = getAlumnoActual(auth);
        if (alumno != null) {
            alumno.setPagoSaberPro(comprobante);
            alumnoService.guardar(alumno);
            ra.addFlashAttribute("mensaje", "Comprobante de pago registrado correctamente");
        }
        return "redirect:/estudiante/pago";
    }

    @GetMapping("/beneficios")
    public String beneficios(Authentication auth, Model model) {
        Alumno alumno = getAlumnoActual(auth);
        if (alumno != null) {
            model.addAttribute("resultados",
                resultadoService.listarPorAlumno(alumno).stream()
                    .filter(ResultadoSaberPro::isTieneBeneficio).toList());
        }
        return "estudiante/beneficios";
    }
}
