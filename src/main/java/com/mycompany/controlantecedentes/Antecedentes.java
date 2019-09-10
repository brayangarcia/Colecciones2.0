/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controlantecedentes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author BRAYAN, ISAAC
 */
public class Antecedentes implements Serializable{
    
    /**
     * Variable de tipo Date para guardar una fecha
     */
    private Date fecha;
    
    /**
     * Variable de tipo Tipo para guardar el tipo
     * de antecedente
     */
    private Tipo tipoAntecedente;
    
    /**
     * Variable de tipo String para guardar la descrpcion
     * del antecendente
     */
    private String descripcion;

    /**
     * Constructor de la clase Antecedentes
     * @param fecha
     * @param tipoAntecedente
     * @param descripcion 
     */
    public Antecedentes(Date fecha, Tipo tipoAntecedente, String descripcion) {
        this.fecha = fecha;
        this.tipoAntecedente = tipoAntecedente;
        this.descripcion = descripcion;
    }   
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tipo getTipoAntecedente() {
        return tipoAntecedente;
    }

    public void setTipoAntecedente(Tipo tipoAntecedente) {
        this.tipoAntecedente = tipoAntecedente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Antecedentes{" + "fecha=" + fecha + ", tipoAntecedente=" + tipoAntecedente + ", descripcion=" + descripcion + '}';
    }
}
