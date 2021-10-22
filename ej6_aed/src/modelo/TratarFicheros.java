/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author Danieldb
 */
public class TratarFicheros {
    
    public TratarFicheros() {
        
    }
    
    public void guardarPrimos (ListaPrimos listaPrimos) {
        File f=new File("C:\\Users\\Danieldb\\Desktop\\primosBytes.txt");
        try{
        FileOutputStream fis=new FileOutputStream(f);
        DataOutputStream dos=new DataOutputStream(fis);
        for (int primo : listaPrimos.listaPrimos) {
                dos.writeInt(primo);
            }
        dos.close();
        } catch(FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        } catch(IOException e){
        System.out.println("Error al escribir");
        }
    }
    
    public String leerPrimos () {
        File f=new File("C:\\Users\\Danieldb\\Desktop\\primosBytes.txt");
        String listaNumeros = "";
        try{
            FileInputStream fis=new FileInputStream(f);
            DataInputStream dis=new DataInputStream(fis);
            int i = 0;
            while (i < 100){
                listaNumeros += dis.readInt() + "\n";
                i++;
            }
            dis.close();
        }
        catch(EOFException e){
            System.out.println("Se ha llegado por fuera del archivo. Esto sucede"
                    + "si quiere llegar hasta al final del archivo.");
        }
        catch(FileNotFoundException e){
            System.out.println("No se encontra el archivo");
        }
        catch(IOException e){
            System.out.println("Error al leer");
        }

        return listaNumeros;
    }
    public static void guardarPrimoAPrimo (int primo) {
        
        File f=new File("C:\\Users\\Danieldb\\Desktop\\primosBytes.txt");
        try{
            FileOutputStream fos=new FileOutputStream(f, true);
            DataOutputStream dos=new DataOutputStream(fos);
            dos.writeInt(primo);
        dos.close();
        } catch(FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        } catch(IOException e){
        System.out.println("Error al escribir");
        }
    }
}
