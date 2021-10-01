/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed_ejer_4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author dama
 */
public class CrearListaPrimos {
    
    public static ArrayList CrearLista() {
        int max = 10000;
        ArrayList<Integer> primos = new ArrayList<>();
        primos.add(2);
        
        for (int primo = 3; primo < max; primo += 2) {
            int i = 0;
            boolean esPrimo = true;
            while (primos.get(i) * primos.get(i) <= primo) {
                if (primo % primos.get(i) == 0) {
                    esPrimo = false;
                }
                i++;
            }
            if (esPrimo) {
                primos.add(primo);
            }
        }
        return primos;
    }
    public static void GuardarFichero() {
        /*JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(areaTexto);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
           File fichero = fileChooser.getSelectedFile();
           try {
                FileWriter myWriter = new FileWriter(fichero.getPath());
                areaTexto.write(myWriter);
                myWriter.close();
        } catch (IOException e) {
                System.out.println("Error al guardar el fichero "
                            + "\n" + e.getMessage());
                }
        }*/
    }
    
    public static void main(String[] args) {
         ArrayList<Integer> primos = CrearLista();
         for (int i = 0; i < primos.size(); i++) {
             System.out.println(primos.get(i));
         }
     }


}
