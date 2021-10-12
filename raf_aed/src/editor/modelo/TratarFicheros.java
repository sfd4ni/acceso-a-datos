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
import java.io.RandomAccessFile;
import java.util.ArrayList;
/**
 *
 * @author Danieldb
 */
public class TratarFicheros {
    final File ficheroDefecto = new File("C:\\Users\\Danieldb\\Documents\\hola8.txt");
    final int SIZE_CHAR = 2;
    public TratarFicheros() {
        
    }
    public String leerRegistro(RandomAccessFile raf, long comienzo, int cantidad) throws IOException {
        raf.seek(comienzo);
        char campo[] = new char[cantidad];
        for (int i = 0; i < cantidad; i++) {
            char c = raf.readChar();
            if (c == '\0') {
                break;
            }
            campo[i] = c;
        }
        return String.valueOf(campo);
    }
    
    public String abrirFichero (File fichero) throws IOException, ClassNotFoundException {
        ListaPersonas listaPersonas = new ListaPersonas();
        try {
        RandomAccessFile rafFichero = new RandomAccessFile(fichero.getAbsolutePath(), "r");
        int i = 0;
            while (i < rafFichero.length()){
                String nombre = leerRegistro(rafFichero, i, 100);
                i = i + 100;
                String apellido =  leerRegistro(rafFichero, i, 100);
                i = i + 100;
                int edad = Integer.parseInt(leerRegistro(rafFichero, i, 6).trim());
                i = i + 6;
                Persona persona = new Persona(nombre, apellido, edad);
                listaPersonas.getListaPersonas().add(persona);
            }
            rafFichero.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listaPersonas.imprimirLista();
    }
    
    public String anadirRegistro (Persona persona) throws IOException, ClassNotFoundException {
        RandomAccessFile rafFichero = new RandomAccessFile(this.ficheroDefecto.getAbsolutePath(), "rw");
        rafFichero.seek(rafFichero.length());
        RegistroPersona registro = new RegistroPersona(persona);
        rafFichero.writeChars(registro.imprimirRegistro());
        return abrirFichero(this.ficheroDefecto);
    }
    
    public String modificarRegistro (Persona persona, long posicion) throws IOException, ClassNotFoundException {
        return guardarRegistro(persona, posicion);
    }
    
    public String guardarRegistro (Persona persona, long posicion) throws FileNotFoundException, IOException, ClassNotFoundException {
        RandomAccessFile rafFichero = new RandomAccessFile(this.ficheroDefecto.getAbsolutePath(), "rw");
        rafFichero.seek(posicion);
        RegistroPersona registro = new RegistroPersona(persona);
        rafFichero.writeChars(registro.imprimirRegistro());
        return abrirFichero(this.ficheroDefecto);
    }
    
    public void guardarFichero (File fichero, ListaPersonas listaPersonas) throws FileNotFoundException, IOException {
        try{
            RandomAccessFile rafFichero = new RandomAccessFile(fichero.getAbsolutePath(), "rw");
            for (Persona persona : listaPersonas.getListaPersonas()) {
                if( rafFichero.length() > 1 ){
                    rafFichero.seek(rafFichero.length());
                }
                RegistroPersona registro = new RegistroPersona(persona);
                
                rafFichero.writeChars(registro.imprimirRegistro());
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
