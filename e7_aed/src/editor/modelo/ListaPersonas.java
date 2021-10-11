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
public class ListaPersonas implements Serializable {
    
    private ArrayList<Persona> listaPersonas; 
    public ListaPersonas() {
        listaPersonas = new ArrayList<>();
        Persona personaUno = new Persona("Daniel", "Barroso", new Dni(46262784, 'D'), 23);
        Persona personaDos = new Persona("Roman", "Barrios", new Dni(22873648, 'X'), 12);
        Persona personaTres = new Persona("Daniel", "García", new Dni(23192495, 'P'), 24);
        Persona personaCuatro = new Persona("Román", "Casto", new Dni(71484756, 'C'), 32);
        Persona personaCinco = new Persona("Samuel", "Roma", new Dni(78115622, 'D'), 34);
        
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
                    + " " + persona.getDni().imprimirDni() + " " + persona.getEdad() + "\n";
        }
        return personaString; 
    }
    
    public ArrayList<Persona> getListaPersonas() {
        return this.listaPersonas;
    }
}
