/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        String textoFichero = "";

        try {
           br = new BufferedReader(new FileReader(nombreFichero));
           String texto = br.readLine();
           while(texto != null) {
               textoFichero += texto + "\n";
               texto = br.readLine();
           } 
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado, mensaje de error:" + e);
        }
        if(br != null)
            br.close();
        return textoFichero;
    }
    
    public void guardarFichero (File fichero, String texto) {
           try {
               try (BufferedWriter bwFile = 
                       new BufferedWriter(new FileWriter(fichero.getPath()))) {
                   bwFile.write(texto);
               }
        } catch (IOException e) {
                System.out.println(e);
                }
    }
}
