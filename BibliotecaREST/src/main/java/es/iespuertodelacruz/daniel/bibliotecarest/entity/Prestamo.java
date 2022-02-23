package es.iespuertodelacruz.daniel.bibliotecarest.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;

/**
 * The persistent class for the prestamos database table.
 * 
 */
@Entity
@Table(name = "prestamos")
@NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")
public class Prestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prestamoid;

	private BigInteger fechadevolucion;

	private BigInteger fechaprestamo;

	@JsonIgnore
	// bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name = "fkclienteid")
	private Cliente cliente;

	// bi-directional many-to-one association to Ejemplare
	@ManyToOne
	@JoinColumn(name = "fkejemplarid")
	private Ejemplare ejemplare;

	public Prestamo() {
	}

	public int getPrestamoid() {
		return this.prestamoid;
	}

	public void setPrestamoid(int prestamoid) {
		this.prestamoid = prestamoid;
	}

	public BigInteger getFechadevolucion() {
		return this.fechadevolucion;
	}

	public void setFechadevolucion(BigInteger fechadevolucion) {
		this.fechadevolucion = fechadevolucion;
	}

	public BigInteger getFechaprestamo() {
		return this.fechaprestamo;
	}

	public void setFechaprestamo(BigInteger fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Ejemplare getEjemplare() {
		return this.ejemplare;
	}

	public void setEjemplare(Ejemplare ejemplare) {
		this.ejemplare = ejemplare;
	}

}