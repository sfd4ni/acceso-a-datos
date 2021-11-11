package es.iespuertodelacruz.daniel.instituto.modelo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Asignatura {
	private int idasignatura;
	private String nombre, curso;
	
	public Asignatura(String nombre, String curso) {
		this.nombre = nombre;
		this.curso = curso;
	}
	
	public int getIdasignatura() {
		return idasignatura;
	}
	public void setIdasignatura(int idasignatura) {
		this.idasignatura = idasignatura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public String imprimir() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String strAlu = mapper
			.writerWithDefaultPrettyPrinter()
			.writeValueAsString(this);
		return strAlu;
	}
}
