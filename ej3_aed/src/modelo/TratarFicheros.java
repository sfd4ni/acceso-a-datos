/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author Danieldb
 */
public class TratarFicheros {
    
    public TratarFicheros() {
        
    }
    
    public String abrirFichero (File fichero) throws IOException {
        String nombreFichero = fichero.getPath();
        BufferedReader br = null;
        String[] personaFichero;
        ListaPersonas listaPersonas = new ListaPersonas();
        
        try {
           br = new BufferedReader(new FileReader(nombreFichero));
           String texto = br.readLine();
           while(texto != null) {
               personaFichero = texto.split(";");
               Persona personaAux = new Persona(personaFichero[0], personaFichero[1],
               personaFichero[2], Integer.parseInt(personaFichero[3]));
               listaPersonas.getListaPersonas().add(personaAux);
               texto = br.readLine();
           } 
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado, mensaje de error:" + e);
        }
        if(br != null)
            br.close();
        return listaPersonas.imprimirLista();
    }
    
    public void guardarFichero (File fichero, ListaPersonas listaPersonas) {
        
        PrintWriter pw = null;
           try {

            BufferedWriter bw = new BufferedWriter( new FileWriter(fichero.getPath()));
            pw = new PrintWriter(bw);
            for (Persona persona : listaPersonas.getListaPersonas()) {
                pw.println(persona.personaToFichero());
            }

            } catch (IOException ex) {
                System.out.println("Error al guardar el fichero" + ex);
            } finally {
                pw.close();
            } 
    }
}
