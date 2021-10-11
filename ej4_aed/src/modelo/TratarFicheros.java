/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedWriter;
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
    
    public void guardarPrimos (ListaPrimos listaPrimos) {
        
        PrintWriter pw = null;
           try {

            BufferedWriter bw = new BufferedWriter( new FileWriter("C:\\Users\\Danieldb\\Desktop\\primos.txt"));
            pw = new PrintWriter(bw);
            for (int primo : listaPrimos.listaPrimos) {
                pw.println(primo);
            }

            } catch (IOException ex) {
                System.out.println("Error al guardar el fichero" + ex);
            } finally {
                pw.close();
            } 
    }
    
    public static void guardarPrimoAPrimo (int primo) {
        
        PrintWriter pw = null;
           try {

            BufferedWriter bw = new BufferedWriter( new FileWriter("C:\\Users\\Danieldb\\Desktop\\primos.txt", true));
            pw = new PrintWriter(bw);

            pw.println(primo);

            } catch (IOException ex) {
                System.out.println("Error al guardar el fichero" + ex);
            } finally {
                pw.close();
            } 
    }
}
