package es.iespuertodelacruz.daniel.bibliotecarest.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the autores database table.
 * 
 */
@Entity
@Table(name="autores")
@NamedQuery(name="Autor.findAll", query="SELECT a FROM Autor a")
public class Autor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int autorid;

	private String apellidos;

	private String nacionalidad;

	private String nombre;

	@JsonIgnore
	//bi-directional many-to-many association to Libro
	/*@ManyToMany
	@JoinTable( name="autor_libro",
	joinColumns = @JoinColumn(name="fkautorid"),
	inverseJoinColumns = @JoinColumn(name="fklibroid")
	)*/
	@ManyToMany(mappedBy="autores")
	private List<Libro> libros;

	public Autor() {
	}

	public int getAutorid() {
		return this.autorid;
	}

	public void setAutorid(int autorid) {
		this.autorid = autorid;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Libro> getLibros() {
		return this.libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

}