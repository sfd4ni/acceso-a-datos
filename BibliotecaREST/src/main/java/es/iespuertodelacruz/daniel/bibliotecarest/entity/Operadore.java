package es.iespuertodelacruz.daniel.bibliotecarest.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the operadores database table.
 * 
 */
@Entity
@Table(name="operadores")
@NamedQuery(name="Operadore.findAll", query="SELECT o FROM Operadore o")
public class Operadore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int operadorid;

	private String nick;

	private String password;

	public Operadore() {
	}

	public int getOperadorid() {
		return this.operadorid;
	}

	public void setOperadorid(int operadorid) {
		this.operadorid = operadorid;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}