/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona_serializable;

/**
 *
 * @author dama
 */
public class Persona {
    
    String nombre;
    int edad;
    Dni dni;
    
    public Persona(String nombreInput, int edadInput, String dniInput) {
        nombre = nombreInput;
        edad = edadInput;
        dni = new Dni(dniInput);
    }
}
