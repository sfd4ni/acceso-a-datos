/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.modelo;

import java.io.Serializable;



/**
 *
 * @author Danieldb
 */
public class Persona implements Serializable {
    private String nombre;
    private String apellido;
    private int edad;
    
    public Persona (String nombreInput, String apellidoInput, 
            int edadInput) {
        this.nombre = nombreInput;
        this.apellido = apellidoInput;
        this.edad = edadInput;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getApellido() {
        return this.apellido;
    }
    
    
    public int getEdad() {
        return this.edad;
    }
    
    public void setNombre(String nombreInput) {
        this.nombre = nombreInput;
    }
    
    public void setApellido(String apellidoInput) {
        this.apellido = apellidoInput;
    }
    
    
    public void setEdad(int edadInput) {
        this.edad = edadInput;
    }
    
    public String personaToFichero() {
        return (this.nombre + ";" + this.apellido + ";" + this.edad);
    }
}
