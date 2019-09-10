/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controlantecedentes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRAYAN, ISAAC
 */
public final class ObtenerDatos {
    /**
     * Metodo encargado para obtener y guardar los datos
     * de una persona y los antecedentes financieros que 
     * esta tenga.
     */
    public ObtenerDatos(){
        ArrayList<Persona> personas;        
        ArrayList<Persona> leerPersonas;
        List<Antecedentes> lAnt = new ArrayList<> ();
        Antecedentes ante;
        Tipo tip;
        Persona per;
        Scanner in = new Scanner(System.in);
        Scanner st = new Scanner(System.in);
        Scanner fe = new Scanner(System.in);
        Date fecha;
        int cont = 0;
        String nombre, cedula, genero, strFecha, descripcion, tDescripcion, nombreCar;        
        byte leer1, edad, leer = 0;
        System.out.println("ANTECEDENTES FINANCIEROS");
        personas = this.verDatos();
        do{            
            System.out.println("Digite la acción que desea realizar: \n 1. Agregar \n 2. Ver(Desde aqui puede Editar)");
            leer1 = in.nextByte();            
            switch(leer1){
                case 1:
                    do{                    
                        if(personas == null){
                            personas = new ArrayList <>();
                        }
                        System.out.println("Digite la cedula:");
                        cedula = st.next();
                        if(repetirCedula(cedula, personas)){
                            System.out.println("La cedula esta repetida.");
                            continue;
                        }
                        System.out.println("Digite el nombre:");
                        nombre = st.next();                        
                        System.out.println("Digite la edad");
                        edad = in.nextByte();
                        System.out.println("Digite el genero (m/f/o)");
                        genero = st.next();
                        do{
                            System.out.println("Insertar un antecedente");
                            System.out.println("Digite la fecha:");
                            strFecha = fe.next();
                            fecha = ParseFecha(strFecha);
                            System.out.println("Escriba la descripcion del antecedente:");
                            descripcion = fe.next();
                            System.out.println("Digite el nombre caracteristico del tipo:");
                            nombreCar = fe.next();
                            System.out.println("Digite la descripcion del tipo:");
                            tDescripcion= fe.next();
                            tip = new Tipo(tDescripcion, nombreCar);
                            ante = new Antecedentes(fecha, tip, descripcion);
                            lAnt.add(ante);                        
                            System.out.println("Para insertar otro antecedente digite 1. Para salir digite otro numero.");
                            leer = in.nextByte();
                        }while(leer == 1);
                        per = new Persona(nombre, cedula, edad, genero.charAt(0), lAnt);
                        personas.add(per);                        
                        System.out.println("Para insertar otra persona digite 1. Para salir digite otro numero.");
                        leer = in.nextByte();                        
                    }while(leer == 1);                    
                    this.guardaDatos(cedula, personas);
                    break;
                case 2:
                    do{                                             
                        int edit;
                        boolean probar;
                        leerPersonas = this.verDatos();
                        probar = imprimirPersonas(leerPersonas);
                        if(!probar){
                            continue;
                        }
                        System.out.println("Digite la persona que desea editar (0 para salir): ");
                        leer = in.nextByte();                        
                        if(leer == 0)
                            break;
                        leerPersonas.get(leer-1);
                        System.out.println("Digite el nuevo nombre: ");
                        leerPersonas.get(leer-1).setNombre(st.next());
                        System.out.println("Digite la nueva edad: ");
                        leerPersonas.get(leer-1).setEdad(in.nextByte());
                        System.out.println("Digite el nuevo genero (m/f/o): ");
                        leerPersonas.get(leer-1).setGenero(in.next().charAt(0));           
                        System.out.println("1 .Agregar un nuevo antecedente \n 2 .Borrar un antecedente \n 3. Salir");
                        edit = in.nextInt();
                        while(edit == 1 || edit == 2){
                            if(edit == 1){                                
                                System.out.println("Insertar un antecedente");
                                System.out.println("Digite la fecha:");
                                strFecha = fe.next();
                                fecha = ParseFecha(strFecha);
                                System.out.println("Escriba la descripcion del antecedente:");
                                descripcion = fe.next();
                                System.out.println("Digite el nombre caracteristico del tipo:");
                                nombreCar = fe.next();
                                System.out.println("Digite la descripcion del tipo:");
                                tDescripcion= fe.next();
                                tip = new Tipo(tDescripcion, nombreCar);
                                ante = new Antecedentes(fecha, tip, descripcion);
                                leerPersonas.get(leer-1).getConjuntoAntecedentes().add(ante);                                 
                            }
                            if(edit == 2){
                                int iterar;                                
                                imprimeAntecedentes(leerPersonas.get(leer-1).getConjuntoAntecedentes());
                                System.out.println("Digite el antecedente que desea eliminar");
                                iterar = in.nextInt();
                                if(leerPersonas.get(leer-1).getConjuntoAntecedentes().get(iterar-1).getTipoAntecedente().getNombreCaracteristico().equals("negativo")){
                                    leerPersonas.get(leer-1).getConjuntoAntecedentes().remove(iterar-1);
                                    System.out.println("Borrando antecedente...");
                                }else{
                                    System.out.println("No se pueden borrar antecedentes positivos");
                                }
                            }
                            if(edit == 3){
                                break;
                            }                            
                            System.out.println("1 .Agregar un nuevo antecedente \n 2 .Borrar un antecedente \n 3 .Salir");
                            edit = in.nextInt();
                        }                        
                        this.editarDatos(leerPersonas);
                    }while(leer !=0);
                    break;
                default:
                    break;
            }
        }while(leer1 == 1 || leer1 == 2 );        
    }
    
    /**
     * Metodo encargado para imprimir los antecedentes
     * @param imprimir 
     */
    void imprimeAntecedentes(List<Antecedentes> imprimir){
        int i = 0;
        System.out.println("Antecedentes de la persona");
        for(Antecedentes ante :imprimir){
            i++;            
            System.out.println("Antecedente n°"+ i);
            System.out.println("Fecha: "+ ante.getFecha());
            System.out.println("Descripcion: " + ante.getDescripcion());
            System.out.println("Nombre Caracteristico " + ante.getTipoAntecedente().getNombreCaracteristico());
            System.out.println("Descripcion: " + ante.getTipoAntecedente().getDescripcion());
        }        
    }
    /**
     * Metodo encargado para editar los datos de una persona 
     * se le envian los datos en un ArrayList para editarlos
     * @param per 
     */
    private void editarDatos(ArrayList<Persona> per){
        ObjectOutputStream escribiendoFichero;
        try {
            escribiendoFichero = new ObjectOutputStream(
                    new FileOutputStream("Personas.dat"));            
            escribiendoFichero.writeObject(per);            
            escribiendoFichero.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ObtenerDatos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Metodo encargado para guardar los datos de una persona
     * se le envia la cedula y un ArrayList de tipo persona
     * @param cedula
     * @param arrayList1 
     */
    void guardaDatos(String cedula, ArrayList<Persona> arrayList1){
        ObjectOutputStream escribiendoFichero;
        try {
            escribiendoFichero = new ObjectOutputStream(
                    new FileOutputStream("Personas.dat"));
            escribiendoFichero.writeObject(arrayList1);            
            escribiendoFichero.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ObtenerDatos.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * ArrayList de tipo persona donde se pueden ver los datos 
     * de una persona.
     * @return 
     */
    public ArrayList<Persona> verDatos() {
        ArrayList<Persona> leerPersonas;
        try{    
            ObjectInputStream leyendoFichero = new ObjectInputStream(
            new FileInputStream("Personas.dat"));
            leerPersonas = ( ArrayList <Persona> )leyendoFichero.readObject();                 
            return leerPersonas;
        }catch (IOException | ClassNotFoundException e) {
            System.out.println( e.getMessage() );
        }
        return null;        
    }
    
    /**
     * Metodo encargado para imprimir los datos de todas 
     * las personas que estan en el archivo. Recibe un
     * ArrayList de tipo persona.
     * @param impri
     * @return 
     */
    public boolean imprimirPersonas(ArrayList<Persona> impri){
        int i = 0;
        if(impri != null){      
            System.out.println("Lista de personas en el archivo");
            for (Persona pr : impri){
                i++;
                System.out.println("Persona n°" + i);
                System.out.println("Nombre : " + pr.getNombre());
                System.out.println("Cedula : " + pr.getCedula());
                System.out.println("Edad : " + pr.getEdad());
                System.out.println("Genero : " + pr.getGenero());
                System.out.println("Cantidad de antecedentes : " + pr.getConjuntoAntecedentes().size());
            }
            return true;
        }
        else{
            System.out.println("No hay personas en el archivo");
            return false;
        }
    }
    
    /**
     * Metodo encargado para validar que las cedulas
     * de las personas no se repitan. Recibe un String cedula 
     * y un ArrayList tipo persona.
     * @param ced
     * @param leerPersonas
     * @return 
     */
    public boolean repetirCedula(String ced, ArrayList<Persona> leerPersonas){        
        boolean rty = false;
        if(leerPersonas == null){
            rty = false;            
        }
        else{       
            for (Persona leerPersona : leerPersonas) {
                if (leerPersona.getCedula().equals(ced)) {
                    rty = true;
                    break;
                }            
                rty = false;
            }
        }
        return rty;
    }
    
    /**
     * Permite convertir un String en fecha (Date).
     * @param fecha Cadena de fecha dd/MM/yyyy
     * @return Objeto Date
     */
    public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }    
}
