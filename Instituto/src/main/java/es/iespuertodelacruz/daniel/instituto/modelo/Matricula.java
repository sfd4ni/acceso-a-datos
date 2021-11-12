package es.iespuertodelacruz.daniel.instituto.modelo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Matricula {
	int idmatricula;
	Alumno alumno;
	
	@JsonProperty("año")
	int year;
	ArrayList<Asignatura> asignaturas;
	
	public Matricula() {
		
	}
	
	public Matricula(int year) {
		this.year = year;
	}
	
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public int getIdmatricula() {
		return idmatricula;
	}
	public void setIdmatricula(int idmatricula) {
		this.idmatricula = idmatricula;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public ArrayList<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	public String imprimir() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String strAlu = mapper
			.writerWithDefaultPrettyPrinter()
			.writeValueAsString(this);
		return strAlu;
	}
}
