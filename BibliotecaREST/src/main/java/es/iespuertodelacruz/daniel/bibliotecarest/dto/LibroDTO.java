package es.iespuertodelacruz.daniel.bibliotecarest.dto;

import java.util.List;



import es.iespuertodelacruz.daniel.bibliotecarest.entity.Autor;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Ejemplare;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Libro;

public class LibroDTO {
	private String editorial;

	private String titulo;

	private List<Autor> autores;
	
	
	public LibroDTO() {}
	
	public LibroDTO(Libro libro) {
		this.editorial = libro.getEditorial();
		this.titulo = libro.getTitulo();
		this.autores = libro.getAutores();
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
}
