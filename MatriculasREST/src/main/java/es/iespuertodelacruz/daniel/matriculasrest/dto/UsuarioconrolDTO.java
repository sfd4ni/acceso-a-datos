package es.iespuertodelacruz.daniel.matriculasrest.dto;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Usuarioconrol;

public class UsuarioconrolDTO {
	private String nombre, rol;

	public UsuarioconrolDTO() {}
	
	public UsuarioconrolDTO(Usuarioconrol usuario) {
		this.nombre = usuario.getNombre();
		this.rol = usuario.getRol();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
