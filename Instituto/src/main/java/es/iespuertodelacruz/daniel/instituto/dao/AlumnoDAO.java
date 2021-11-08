package es.iespuertodelacruz.daniel.instituto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import es.iespuertodelacruz.daniel.instituto.modelo.Alumno;

public class AlumnoDAO implements Crud<Alumno, String> {

	GestorConexionesDDBB gc;
	public AlumnoDAO(GestorConexionesDDBB gc) {
		this.gc = gc;
	}
	@Override
	public Alumno save(Alumno alumno) {
		Alumno resultado = null;
		String query="INSERT INTO `alumnos` "
				+ "(`dni`, `nombre`, `apellidos`, `fechanacimiento`)"
				+ " VALUES (?, ?, ?, ?)";
		try (
		Connection cn = gc.getConnection();
		 PreparedStatement ps = cn.prepareStatement(query);
		 ){
			 ps.setString(1, alumno.getDni());
			 ps.setString(2, alumno.getNombre());
			 ps.setString(3, alumno.getApellidos());
			 ps.setLong(4, alumno.getFechaNacimiento().getTime());
			 ps.executeUpdate();
			 ResultSet rs = ps.getGeneratedKeys();
			 while(rs.next()){
				 String dni = rs.getString(1);
				 String nombre = rs.getString(2);
				 String apellidos = rs.getString(3);
				 long fechaNacimiento = rs.getLong(4);
				 resultado = new Alumno(dni, nombre, apellidos, new Date(fechaNacimiento));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return resultado;
	}

	@Override
	public Alumno findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Alumno dao) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Alumno> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
