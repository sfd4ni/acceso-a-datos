package es.iespuertodelacruz.daniel.instituto.modelo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Matricula {
	int idmatricula;
	Alumno alumno;
	@JsonProperty("año")
	int year;
	//ArrayList<Asignatura> asignaturas;
}
