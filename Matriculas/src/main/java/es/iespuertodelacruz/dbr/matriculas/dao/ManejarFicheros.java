package es.iespuertodelacruz.dbr.matriculas.dao;

import java.io.File;

public class ManejarFicheros {
	File file;
	
	public ManejarFicheros(String ruta) {
		file = new File(ruta);
	}
}
