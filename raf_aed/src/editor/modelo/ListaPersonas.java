/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.modelo;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Danieldb
 */
public class ListaPersonas {
    
    private ArrayList<Persona> listaPersonas; 
    public ListaPersonas() {
        listaPersonas = new ArrayList<>();
        Persona personaUno = new Persona("Daniel", "Barroso", 23);
        Persona personaDos = new Persona("Roman", "Barrios", 12);
        Persona personaTres = new Persona("Daniel", "García", 24);
        Persona personaCuatro = new Persona("Román", "Casto", 32);
        Persona personaCinco = new Persona("Samuel", "Roma", 34);
        
        listaPersonas.add(personaUno);
        listaPersonas.add(personaDos);
        listaPersonas.add(personaTres);
        listaPersonas.add(personaCuatro);
        listaPersonas.add(personaCinco);
        
    }
    
    
    public String imprimirLista() {
        String personaString = "Nombre Apellido DNI Edad\n";
        
        for (Persona persona : listaPersonas) {
            personaString += persona.getNombre() + " " + persona.getApellido() 
                    + " " + persona.getEdad() + "\n";
        }
        return personaString; 
    }
    
    public ArrayList<Persona> getListaPersonas() {
        return this.listaPersonas;
    }
}
