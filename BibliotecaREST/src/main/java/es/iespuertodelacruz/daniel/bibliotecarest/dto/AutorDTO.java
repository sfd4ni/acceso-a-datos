package es.iespuertodelacruz.daniel.bibliotecarest.dto;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Autor;

public class AutorDTO {
	private String apellidos;

	private String nacionalidad;

	private String nombre;

	public AutorDTO() {
	}

	public AutorDTO(Autor autor) {
		this.apellidos = autor.getApellidos();
		this.nacionalidad = autor.getNacionalidad();
		this.nombre = autor.getNombre();
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
