package com.saberpro.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"))) {
            return "redirect:/admin/dashboard";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_COORDINACION"))) {
            return "redirect:/coordinacion/dashboard";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DOCENTE"))) {
            return "redirect:/docente/dashboard";
        } else {
            return "redirect:/estudiante/dashboard";
        }
    }
}
