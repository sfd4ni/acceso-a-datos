package es.iespuertodelacruz.daniel.pruebarest.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the historicocambioeuro database table.
 * 
 */
@Entity
@Table(name="historicocambioeuro")
@NamedQuery(name="Historicocambioeuro.findAll", query="SELECT h FROM Historicocambioeuro h")
public class Historicocambioeuro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idhistoricocambioeuro;

	private BigDecimal equivalenteeuro;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="fkidmoneda")
	private Moneda moneda;

	public Historicocambioeuro() {
	}

	public int getIdhistoricocambioeuro() {
		return this.idhistoricocambioeuro;
	}

	public void setIdhistoricocambioeuro(int idhistoricocambioeuro) {
		this.idhistoricocambioeuro = idhistoricocambioeuro;
	}

	public BigDecimal getEquivalenteeuro() {
		return this.equivalenteeuro;
	}

	public void setEquivalenteeuro(BigDecimal equivalenteeuro) {
		this.equivalenteeuro = equivalenteeuro;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

}