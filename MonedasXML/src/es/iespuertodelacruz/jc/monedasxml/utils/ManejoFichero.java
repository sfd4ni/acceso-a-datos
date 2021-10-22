/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class ManejoFichero {
    
    
    File file;
    
    
    public ManejoFichero(String nombre){
        file = new File(nombre);
    }
    
    public boolean agregarTexto(String texto){
        PrintWriter pw = null;
        boolean okFichero = true;
           try {

            BufferedWriter bw = new BufferedWriter( new FileWriter(file.getAbsolutePath(), true));
            pw = new PrintWriter(bw);

            pw.println(texto);

            } catch (IOException ex) {
                okFichero = false;
                System.out.println("Error al guardar el fichero" + ex);
            } finally {
                pw.close();
            }
           return okFichero;
    }
    
    public boolean borrarYAgregar(String texto){
        PrintWriter pw = null;
        boolean okFichero = true;
           try {

            BufferedWriter bw = new BufferedWriter( new FileWriter(file.getAbsolutePath()));
            pw = new PrintWriter(bw, true);

            pw.println(texto);

            } catch (IOException ex) {
                okFichero = false;
                System.out.println("Error al guardar el fichero" + ex);
            } finally {
                pw.close();
            }
           return okFichero;  
    }
    
    public String leerTodo() throws IOException {
        String nombreFichero = file.getPath();
        BufferedReader br = null;
        String xmlString = "";
        try {
           br = new BufferedReader(new FileReader(nombreFichero));
           String texto = br.readLine();
           while(texto != null) {
               System.out.println(texto);
               xmlString += texto;
               texto = br.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado, mensaje de error:" + e);
        }
        if(br != null)
            br.close();

        return xmlString;
    }
    
}
