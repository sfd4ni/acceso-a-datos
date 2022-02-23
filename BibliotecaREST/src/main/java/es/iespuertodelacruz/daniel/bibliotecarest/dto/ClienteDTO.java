package es.iespuertodelacruz.daniel.bibliotecarest.dto;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Cliente;

public class ClienteDTO {

	private String apellidos;

	private String direccion;

	private String nombre;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente cliente) {
		this.apellidos = cliente.getApellidos();
		this.direccion = cliente.getDireccion();
		this.nombre = cliente.getNombre();
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
