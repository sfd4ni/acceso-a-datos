package es.iespuertodelacruz.jc.monedaswebjpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
@NamedQuery(name="Usuario.findByName", query="SELECT u FROM Usuario u WHERE u.nombre = :name")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idusuario;

	@Column(nullable=false, length=45)
	private String nombre;

	@Column(nullable=false, length=200)
	private String password;

	//bi-directional many-to-many association to Rol
	@ManyToMany
	@JoinTable( name="usuario_rol",
		joinColumns = @JoinColumn(name="fk_usuario"),
		inverseJoinColumns = @JoinColumn(name="fk_rol")
	) 
	private List<Rol> rols;

	public Usuario() {
	}

	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

}