package com.clubes.clubes.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pruebas_tyt")
public class PruebaTyT {
    @Id
    private String id;
    private String usuarioId;
    private String numeroRegistro;
    private int puntajeGlobal;
    private int comunicacionEscrita;
    private String comunicacionEscritaNivel;
    private int razonamientoCuantitativo;
    private String razonamientoCuantitativoNivel;
    private int lecturaCritica;
    private String lecturaCriticaNivel;
    private int competenciasCiudadanas;
    private String competenciasCiudadanasNivel;
    private int ingles;
    private int formulacionProyectosIngenieria;
    private String formulacionProyectosIngenieriaNivel;
    private int pensamientoCientificoMatematicasEstadistica;
    private String pensamientoCientificoMatematicasEstadisticaNivel;
    private int disenoSoftware;
    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getNumeroRegistro() { return numeroRegistro; }
    public void setNumeroRegistro(String numeroRegistro) { this.numeroRegistro = numeroRegistro; }
    public int getPuntajeGlobal() { return puntajeGlobal; }
    public void setPuntajeGlobal(int puntajeGlobal) { this.puntajeGlobal = puntajeGlobal; }
    public int getComunicacionEscrita() { return comunicacionEscrita; }
    public void setComunicacionEscrita(int comunicacionEscrita) { this.comunicacionEscrita = comunicacionEscrita; }
    public String getComunicacionEscritaNivel() { return comunicacionEscritaNivel; }
    public void setComunicacionEscritaNivel(String comunicacionEscritaNivel) { this.comunicacionEscritaNivel = comunicacionEscritaNivel; }
    public int getRazonamientoCuantitativo() { return razonamientoCuantitativo; }
    public void setRazonamientoCuantitativo(int razonamientoCuantitativo) { this.razonamientoCuantitativo = razonamientoCuantitativo; }
    public String getRazonamientoCuantitativoNivel() { return razonamientoCuantitativoNivel; }
    public void setRazonamientoCuantitativoNivel(String razonamientoCuantitativoNivel) { this.razonamientoCuantitativoNivel = razonamientoCuantitativoNivel; }
    public int getLecturaCritica() { return lecturaCritica; }
    public void setLecturaCritica(int lecturaCritica) { this.lecturaCritica = lecturaCritica; }
    public String getLecturaCriticaNivel() { return lecturaCriticaNivel; }
    public void setLecturaCriticaNivel(String lecturaCriticaNivel) { this.lecturaCriticaNivel = lecturaCriticaNivel; }
    public int getCompetenciasCiudadanas() { return competenciasCiudadanas; }
    public void setCompetenciasCiudadanas(int competenciasCiudadanas) { this.competenciasCiudadanas = competenciasCiudadanas; }
    public String getCompetenciasCiudadanasNivel() { return competenciasCiudadanasNivel; }
    public void setCompetenciasCiudadanasNivel(String competenciasCiudadanasNivel) { this.competenciasCiudadanasNivel = competenciasCiudadanasNivel; }
    public int getIngles() { return ingles; }
    public void setIngles(int ingles) { this.ingles = ingles; }
    public int getFormulacionProyectosIngenieria() { return formulacionProyectosIngenieria; }
    public void setFormulacionProyectosIngenieria(int formulacionProyectosIngenieria) { this.formulacionProyectosIngenieria = formulacionProyectosIngenieria; }
    public String getFormulacionProyectosIngenieriaNivel() { return formulacionProyectosIngenieriaNivel; }
    public void setFormulacionProyectosIngenieriaNivel(String formulacionProyectosIngenieriaNivel) { this.formulacionProyectosIngenieriaNivel = formulacionProyectosIngenieriaNivel; }
    public int getPensamientoCientificoMatematicasEstadistica() { return pensamientoCientificoMatematicasEstadistica; }
    public void setPensamientoCientificoMatematicasEstadistica(int pensamientoCientificoMatematicasEstadistica) { this.pensamientoCientificoMatematicasEstadistica = pensamientoCientificoMatematicasEstadistica; }
    public String getPensamientoCientificoMatematicasEstadisticaNivel() { return pensamientoCientificoMatematicasEstadisticaNivel; }
    public void setPensamientoCientificoMatematicasEstadisticaNivel(String pensamientoCientificoMatematicasEstadisticaNivel) { this.pensamientoCientificoMatematicasEstadisticaNivel = pensamientoCientificoMatematicasEstadisticaNivel; }
    public int getDisenoSoftware() { return disenoSoftware; }
    public void setDisenoSoftware(int disenoSoftware) { this.disenoSoftware = disenoSoftware; }
}
