package es.iespuertodelacruz.daniel.bibliotecarest.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the libros database table.
 * 
 */
@Entity
@Table(name="libros")
@NamedQuery(name="Libro.findAll", query="SELECT l FROM Libro l")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int libroid;

	private String editorial;

	private String titulo;

	@JsonIgnore
	//bi-directional many-to-one association to Ejemplare
	@OneToMany(mappedBy="libro")
	private List<Ejemplare> ejemplares;

	//bi-directional many-to-many association to Autor
	@ManyToMany(mappedBy="libros")
	private List<Autor> autores;

	public Libro() {
	}

	public int getLibroid() {
		return this.libroid;
	}

	public void setLibroid(int libroid) {
		this.libroid = libroid;
	}

	public String getEditorial() {
		return this.editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Ejemplare> getEjemplares() {
		return this.ejemplares;
	}

	public void setEjemplares(List<Ejemplare> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public Ejemplare addEjemplare(Ejemplare ejemplare) {
		getEjemplares().add(ejemplare);
		ejemplare.setLibro(this);

		return ejemplare;
	}

	public Ejemplare removeEjemplare(Ejemplare ejemplare) {
		getEjemplares().remove(ejemplare);
		ejemplare.setLibro(null);

		return ejemplare;
	}

	public List<Autor> getAutores() {
		return this.autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

}