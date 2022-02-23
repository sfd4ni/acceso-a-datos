package es.iespuertodelacruz.daniel.bibliotecarest.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Cliente;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Ejemplare;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Prestamo;

public class PrestamoDTO {
	
	private int prestamoid;
	
	private Ejemplare ejemplar;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaprestamo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechadevolucion;

	public PrestamoDTO() {
		
	}
	
	public PrestamoDTO(Prestamo prestamo) {
		this.prestamoid = prestamo.getPrestamoid(); 
		this.ejemplar = prestamo.getEjemplare();
		Date fechaprestamo = new Date(prestamo.getFechaprestamo().longValue());
		if (prestamo.getFechaprestamo() != null) {
			this.fechaprestamo = fechaprestamo;
		}
		
		if (prestamo.getFechadevolucion() != null) {
			Date fechadevolucion = new Date(prestamo.getFechadevolucion().longValue());
			this.fechadevolucion = fechadevolucion;
		}
		
	}
	
	

	public int getPrestamoid() {
		return prestamoid;
	}

	public void setPrestamoid(int prestamoid) {
		this.prestamoid = prestamoid;
	}

	public Ejemplare getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplare ejemplar) {
		this.ejemplar = ejemplar;
	}

	public Date getFechaprestamo() {
		return fechaprestamo;
	}

	public void setFechaprestamo(Date fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}

	public Date getFechadevolucion() {
		return fechadevolucion;
	}

	public void setFechadevolucion(Date fechadevolucion) {
		this.fechadevolucion = fechadevolucion;
	}
	
}
