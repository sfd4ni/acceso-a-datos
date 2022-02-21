package es.iespuertodelacruz.daniel.bibliotecarest.dto;

import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Cliente;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Prestamo;

public class ClienteGET {
	
	private String apellidos;

	private String direccion;

	private String nombre;
	
	private List<PrestamoDTO> prestamos;
	
	public ClienteGET() {}
	
	public ClienteGET(Cliente cliente) {
		this.apellidos = cliente.getApellidos();
		this.direccion = cliente.getDireccion();
		this.nombre = cliente.getNombre();
		this.prestamos = new ArrayList<>();
		for (Prestamo prestamo : cliente.getPrestamos()) {
			this.prestamos.add(new PrestamoDTO(prestamo));
		}
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

	public List<PrestamoDTO> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<PrestamoDTO> prestamos) {
		this.prestamos = prestamos;
	}
	
}
