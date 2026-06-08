package com.saberpro.config;

import com.saberpro.model.*;
import com.saberpro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private FacultadRepository facultadRepo;
    @Autowired private DirectorRepository directorRepo;
    @Autowired private DocenteRepository docenteRepo;
    @Autowired private AlumnoRepository alumnoRepo;
    @Autowired private ResultadoSaberProRepository resultadoRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // --- Facultades ---
        Facultad fTecno = new Facultad();
        fTecno.setNombre("Tecnología e Ingeniería");
        fTecno.setDescripcion("Facultad de Ingeniería de Sistemas y afines");
        facultadRepo.save(fTecno);

        Facultad fAdmon = new Facultad();
        fAdmon.setNombre("Administración de Empresas");
        fAdmon.setDescripcion("Facultad de Ciencias Administrativas");
        facultadRepo.save(fAdmon);

        // --- Usuarios ---
        Usuario uAdmin = new Usuario();
        uAdmin.setUsername("admin");
        uAdmin.setPassword(passwordEncoder.encode("admin123"));
        uAdmin.setEmail("admin@saberpro.edu.co");
        uAdmin.setRol(Rol.ADMINISTRADOR);
        usuarioRepo.save(uAdmin);

        Usuario uCoord = new Usuario();
        uCoord.setUsername("coordinacion");
        uCoord.setPassword(passwordEncoder.encode("coord123"));
        uCoord.setEmail("coordinacion@saberpro.edu.co");
        uCoord.setRol(Rol.COORDINACION);
        usuarioRepo.save(uCoord);

        Usuario uDocente = new Usuario();
        uDocente.setUsername("docente");
        uDocente.setPassword(passwordEncoder.encode("docente123"));
        uDocente.setEmail("docente@saberpro.edu.co");
        uDocente.setRol(Rol.DOCENTE);
        usuarioRepo.save(uDocente);

        Usuario uEstudiante = new Usuario();
        uEstudiante.setUsername("estudiante");
        uEstudiante.setPassword(passwordEncoder.encode("est123"));
        uEstudiante.setEmail("estudiante@saberpro.edu.co");
        uEstudiante.setRol(Rol.ESTUDIANTE);
        usuarioRepo.save(uEstudiante);

        // --- Director ---
        Director director = new Director();
        director.setNombre("Carlos");
        director.setApellido("Ramírez");
        director.setCedula("1000111222");
        director.setEmail("cramirez@saberpro.edu.co");
        director.setTelefono("3001234567");
        director.setFacultad(fTecno);
        directorRepo.save(director);

        // --- Docente ---
        Docente docente = new Docente();
        docente.setNombre("María");
        docente.setApellido("González");
        docente.setCedula("1000333444");
        docente.setEmail("mgonzalez@saberpro.edu.co");
        docente.setFacultad(fTecno);
        docente.setUsuario(uDocente);
        docenteRepo.save(docente);

        // --- Alumnos ---
        Alumno a1 = new Alumno();
        a1.setNombre("Juan");
        a1.setApellido("Pérez");
        a1.setCedula("1098765432");
        a1.setEmail("jperez@correo.com");
        a1.setFechaNacimiento(LocalDate.of(2001, 5, 15));
        a1.setFacultad(fTecno);
        a1.setAprobadoSaberPro(true);
        a1.setUsuario(uEstudiante);
        alumnoRepo.save(a1);

        Alumno a2 = new Alumno();
        a2.setNombre("Laura");
        a2.setApellido("Martínez");
        a2.setCedula("1087654321");
        a2.setEmail("lmartinez@correo.com");
        a2.setFechaNacimiento(LocalDate.of(2000, 8, 22));
        a2.setFacultad(fTecno);
        a2.setAprobadoSaberPro(false);
        alumnoRepo.save(a2);

        // --- Resultados ---
        ResultadoSaberPro r1 = new ResultadoSaberPro();
        r1.setAlumno(a1);
        r1.setFechaExamen(LocalDate.of(2024, 11, 20));
        r1.setPuntajeTotal(185.5);
        r1.setPuntajeLecturaCritica(175.0);
        r1.setPuntajeRazonamientoCuantitativo(190.0);
        r1.setPuntajeCompetenciasCiudadanas(180.0);
        r1.setPuntajeComunicacionEscrita(185.0);
        r1.setPuntajeIngles(200.0);
        r1.setPeriodo("2024-2");
        r1.setTipoResultado("TOTAL");
        r1.setTieneBeneficio(true);
        r1.setDescripcionBeneficio("Resolución Beneficio Tecnología e Ingeniería - Excelencia académica");
        resultadoRepo.save(r1);

        ResultadoSaberPro r2 = new ResultadoSaberPro();
        r2.setAlumno(a1);
        r2.setFechaExamen(LocalDate.of(2024, 11, 20));
        r2.setPuntajeTotal(185.5);
        r2.setPuntajeLecturaCritica(175.0);
        r2.setPuntajeRazonamientoCuantitativo(190.0);
        r2.setPeriodo("2024-2");
        r2.setTipoResultado("UNICO");
        r2.setTieneBeneficio(false);
        resultadoRepo.save(r2);

        System.out.println("=== DATOS DE PRUEBA CARGADOS ===");
        System.out.println("admin / admin123 -> ROL: ADMINISTRADOR");
        System.out.println("coordinacion / coord123 -> ROL: COORDINACION");
        System.out.println("docente / docente123 -> ROL: DOCENTE");
        System.out.println("estudiante / est123 -> ROL: ESTUDIANTE");
    }
}
