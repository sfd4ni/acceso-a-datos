package es.iespuertodelacruz.daniel.bibliotecarest.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ejemplares database table.
 * 
 */
@Entity
@Table(name="ejemplares")
@NamedQuery(name="Ejemplare.findAll", query="SELECT e FROM Ejemplare e")
public class Ejemplare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ejemplarid;

	private String localizacion;

	//bi-directional many-to-one association to Libro
	@ManyToOne
	@JoinColumn(name="fklibroid")
	private Libro libro;

	//bi-directional many-to-one association to Prestamo
	@OneToMany(mappedBy="ejemplare")
	private List<Prestamo> prestamos;

	public Ejemplare() {
	}

	public int getEjemplarid() {
		return this.ejemplarid;
	}

	public void setEjemplarid(int ejemplarid) {
		this.ejemplarid = ejemplarid;
	}

	public String getLocalizacion() {
		return this.localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public List<Prestamo> getPrestamos() {
		return this.prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public Prestamo addPrestamo(Prestamo prestamo) {
		getPrestamos().add(prestamo);
		prestamo.setEjemplare(this);

		return prestamo;
	}

	public Prestamo removePrestamo(Prestamo prestamo) {
		getPrestamos().remove(prestamo);
		prestamo.setEjemplare(null);

		return prestamo;
	}

}