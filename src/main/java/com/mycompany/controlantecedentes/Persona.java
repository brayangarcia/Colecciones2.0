/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controlantecedentes;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author BRAYAN, ISAAC
 */
public class Persona implements Serializable{
    /**
     * Variable de tipo String para guardar
     * el nombre de la persona
     */
    private String nombre;
    /**
     * Variable de tipo String para guardar
     * la cedula de la persona
     */
    private String cedula;      
    /**
     * Variable de tipo byte para guardar
     * la edad de la persona
     */
    private byte edad;
    /**
     * Variable de tipo char para guardar
     * el genero de la persona
     */
    private char genero;
    
    private List<Antecedentes> conjuntoAntecedentes;

    /**
     * Constructor de la clase Persona
     * @param nombre
     * @param cedula
     * @param edad
     * @param genero
     * @param conjuntoAntecedentes 
     */
    public Persona(String nombre, String cedula, byte edad, char genero, List<Antecedentes> conjuntoAntecedentes) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.genero = genero;
        this.conjuntoAntecedentes = conjuntoAntecedentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public List<Antecedentes> getConjuntoAntecedentes() {
        return conjuntoAntecedentes;
    }

    public void setConjuntoAntecedentes(List<Antecedentes> conjuntoAntecedentes) {
        this.conjuntoAntecedentes = conjuntoAntecedentes;
    }

    @Override
    public String toString() {
        return "Persona{" + "Nombre=" + nombre + ", Cedula=" + cedula + ", Edad=" + edad + ", Genero=" + genero + ", Conjunto Antecedentes=" + conjuntoAntecedentes + '}';
    }
    
    
    
}
