package es.iespuertodelacruz.dbr.matriculas.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManejarFicheros {
	private final File file;
	
	public ManejarFicheros(String ruta) {
		file = new File(ruta);
	}
	
	public void guardarSecreto(String nombreUsuario, int numSecreto, long tiempo) {
		try
		{
		    FileWriter fw = new FileWriter(file.getAbsolutePath(),true); //the true will append the new data
		    fw.write(nombreUsuario + ";" + numSecreto + ";" + tiempo + "\n");//appends the string to the file
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
}
