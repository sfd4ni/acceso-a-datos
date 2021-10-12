/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.modelo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;



/**
 *
 * @author Danieldb
 */
public class RegistroPersona {
    public String nombreRegistro;
    public String apellidoRegistro;
    public String edadRegistro;
    final int SIZE_CHAR = 2;
    
    public RegistroPersona(Persona persona) {
        personaToRegistro(persona);
    }
    
    public void personaToRegistro(Persona persona) {
        this.nombreRegistro = rellenarCampo(persona.getNombre(), 50);
        this.apellidoRegistro = rellenarCampo(persona.getApellido(), 50);
        this.edadRegistro = rellenarCampo("" + persona.getEdad(), 3);
    }
    

    public String imprimirRegistro() {
        return this.nombreRegistro + this.apellidoRegistro + this.edadRegistro;
    }
    
    public String rellenarCampo(String campo, int tamano) {
        char[] campoRegistro = new char[tamano];
        for (int i = 0; i < campo.length(); i++) {
            campoRegistro[i] = campo.charAt(i);
        }
        campoRegistro[campo.length()] = '\0';
        for (int i = campo.length() + 1; i < tamano; i++) {
            campoRegistro[i] = ' ';
        }
        return String.valueOf(campoRegistro);
    }
    
    
}
