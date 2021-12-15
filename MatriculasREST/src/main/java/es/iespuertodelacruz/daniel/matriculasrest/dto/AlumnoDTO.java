package es.iespuertodelacruz.daniel.matriculasrest.dto;

import java.math.BigInteger;
import java.util.Date;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;

public class AlumnoDTO {
	public AlumnoDTO() {}
	private String dni, nombre, apellidos;
	
	private BigInteger fechaNacimiento;
	
	public AlumnoDTO(Alumno alumno) {
		dni = alumno.getDni();
		nombre = alumno.getNombre();
		apellidos = alumno.getApellidos();
		fechaNacimiento = alumno.getFechanacimiento();
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public BigInteger getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(BigInteger fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
