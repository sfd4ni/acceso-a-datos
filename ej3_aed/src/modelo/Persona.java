/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Danieldb
 */
public class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private int edad;
    
    public Persona (String nombreInput, String apellidoInput, 
            String dniInput, int edadInput) {
        this.nombre = nombreInput;
        this.apellido = apellidoInput;
        this.dni = dniInput;
        this.edad = edadInput;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getApellido() {
        return this.apellido;
    }
    
    public String getDni() {
        return this.dni;
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
    
    public void setDni(String dniInput) {
        this.dni = dniInput;
    }
    
    public void setEdad(int edadInput) {
        this.edad = edadInput;
    }
    
    public String personaToFichero() {
        return (this.nombre + ";" + this.apellido + ";" + this.dni + ";" + this.edad);
    }
}
