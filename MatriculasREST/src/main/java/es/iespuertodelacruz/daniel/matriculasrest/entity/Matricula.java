package es.iespuertodelacruz.daniel.matriculasrest.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the matriculas database table.
 * 
 */
@Entity
@Table(name="matriculas")
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idmatricula;

	private int year;

	//bi-directional many-to-one association to Alumno
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="dni")
	private Alumno alumno;


	//bi-directional many-to-many association to Asignatura
	@ManyToMany(mappedBy="matriculas")
	private List<Asignatura> asignaturas;

	public Matricula() {
	}

	public int getIdmatricula() {
		return this.idmatricula;
	}

	public void setIdmatricula(int idmatricula) {
		this.idmatricula = idmatricula;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Asignatura> getAsignaturas() {
		return this.asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

}