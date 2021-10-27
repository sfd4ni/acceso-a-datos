package es.iespuertodelacruz.dbr.matriculas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import es.iespuertodelacruz.dbr.matriculas.modelo.Mensaje;

public class ManejarFicheros {
	private final File file;
	
	public ManejarFicheros(String ruta) {
		file = new File(ruta);
	}
	
	public void guardarTodo(ArrayList<Mensaje> listaMensajes) {
		PrintWriter pw = null;
        try {

         BufferedWriter bw = new BufferedWriter( new FileWriter(this.file.getPath()));
         pw = new PrintWriter(bw);
         for (Mensaje mensaje : listaMensajes) {
             pw.println(mensaje.getNombreUsuario() + ";" + mensaje.getTexto());
         }

         } catch (IOException ex) {
             System.out.println("Error al guardar el fichero" + ex);
         } finally {
             pw.close();
         } 
	}
	
	public ArrayList<Mensaje> leerTodo() throws IOException {
		ArrayList<Mensaje> listaMensajes = new ArrayList<>();
		BufferedReader br = null;
		String[] mensajeFichero;
        
        try {
           br = new BufferedReader(new FileReader(this.file.getAbsolutePath()));
           String texto = br.readLine();
           while(texto != null) {
               mensajeFichero = texto.split(";");
               Mensaje mensajeAux = new Mensaje(mensajeFichero[0], mensajeFichero[1]);
               listaMensajes.add(mensajeAux);
               texto = br.readLine();
           } 
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado, mensaje de error:" + e);
        }
        if(br != null)
            br.close();
		
		return listaMensajes;
	}
	/*public long ultimaModificacion() {
		long resultado = null;
		try {
			attr = BasicFileAttributes(file.toPath(), BasicFileAttributes.class);
			resultado = attr.lastModifiedTime().to
		}
	}*/
}
