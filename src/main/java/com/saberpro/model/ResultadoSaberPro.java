package com.saberpro.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resultados_saber_pro")
public class ResultadoSaberPro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @Column private LocalDate fechaExamen;
    @Column private Double puntajeTotal;
    @Column private Double puntajeLecturaCritica;
    @Column private Double puntajeRazonamientoCuantitativo;
    @Column private Double puntajeCompetenciasCiudadanas;
    @Column private Double puntajeComunicacionEscrita;
    @Column private Double puntajeIngles;
    @Column private String periodo;
    @Column private String tipoResultado;
    @Column private boolean tieneBeneficio = false;
    @Column private String descripcionBeneficio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Alumno getAlumno() { return alumno; }
    public void setAlumno(Alumno alumno) { this.alumno = alumno; }
    public LocalDate getFechaExamen() { return fechaExamen; }
    public void setFechaExamen(LocalDate fechaExamen) { this.fechaExamen = fechaExamen; }
    public Double getPuntajeTotal() { return puntajeTotal; }
    public void setPuntajeTotal(Double puntajeTotal) { this.puntajeTotal = puntajeTotal; }
    public Double getPuntajeLecturaCritica() { return puntajeLecturaCritica; }
    public void setPuntajeLecturaCritica(Double puntajeLecturaCritica) { this.puntajeLecturaCritica = puntajeLecturaCritica; }
    public Double getPuntajeRazonamientoCuantitativo() { return puntajeRazonamientoCuantitativo; }
    public void setPuntajeRazonamientoCuantitativo(Double puntajeRazonamientoCuantitativo) { this.puntajeRazonamientoCuantitativo = puntajeRazonamientoCuantitativo; }
    public Double getPuntajeCompetenciasCiudadanas() { return puntajeCompetenciasCiudadanas; }
    public void setPuntajeCompetenciasCiudadanas(Double puntajeCompetenciasCiudadanas) { this.puntajeCompetenciasCiudadanas = puntajeCompetenciasCiudadanas; }
    public Double getPuntajeComunicacionEscrita() { return puntajeComunicacionEscrita; }
    public void setPuntajeComunicacionEscrita(Double puntajeComunicacionEscrita) { this.puntajeComunicacionEscrita = puntajeComunicacionEscrita; }
    public Double getPuntajeIngles() { return puntajeIngles; }
    public void setPuntajeIngles(Double puntajeIngles) { this.puntajeIngles = puntajeIngles; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public String getTipoResultado() { return tipoResultado; }
    public void setTipoResultado(String tipoResultado) { this.tipoResultado = tipoResultado; }
    public boolean isTieneBeneficio() { return tieneBeneficio; }
    public void setTieneBeneficio(boolean tieneBeneficio) { this.tieneBeneficio = tieneBeneficio; }
    public String getDescripcionBeneficio() { return descripcionBeneficio; }
    public void setDescripcionBeneficio(String descripcionBeneficio) { this.descripcionBeneficio = descripcionBeneficio; }
}
