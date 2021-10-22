/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.modelo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author Danieldb
 */
public class TratarFicheros {
    
    public TratarFicheros() {
        
    }
    
    public String abrirFichero (File fichero) throws IOException, ClassNotFoundException {
        ListaPersonas personas = new ListaPersonas();
        
        try(
            FileInputStream fis = new FileInputStream(fichero);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
        ){
            boolean finDeFichero = false;
                Persona p;
            do{
            try{
                p = (Persona)ois.readObject();
                personas.getListaPersonas().add(p);
            }catch(EOFException ex){
                finDeFichero = true;
            }
            }   while(!finDeFichero);
        } catch (FileNotFoundException ex) {}
        return personas.imprimirLista();
    }
    
    public void guardarFichero (File fichero, ListaPersonas listaPersonas) {
        try(
            FileOutputStream fos = new FileOutputStream(fichero);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            ) {
            for(Persona persona : listaPersonas.getListaPersonas()) {
                oos.writeObject(persona);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
