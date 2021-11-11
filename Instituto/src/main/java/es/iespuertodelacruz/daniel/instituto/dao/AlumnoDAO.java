package es.iespuertodelacruz.daniel.instituto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import es.iespuertodelacruz.daniel.instituto.contracts.AlumnoContract;
import es.iespuertodelacruz.daniel.instituto.modelo.Alumno;

public class AlumnoDAO implements Crud<Alumno, String> {

	GestorConexionesDDBB gc;
	public AlumnoDAO(GestorConexionesDDBB gc) {
		this.gc = gc;
	}
	@Override
	public Alumno save(Alumno alumno) {
		Alumno resultado = null;
		String query="INSERT INTO `" + AlumnoContract.TABLE_NAME + "` "
				+ "(`" + AlumnoContract.DNI + "`, `" + AlumnoContract.NOMBRE + "`, `"
						+ "" + AlumnoContract.APELLIDOS + "`, `" + AlumnoContract.FECHA_NACIMIENTO + "`)"
				+ " VALUES (?, ?, ?, ?)";
		try (
		Connection cn = gc.getConnection();
		 PreparedStatement ps = cn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
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
	public Alumno findById(String dni) {
		Alumno alumno = null;
		String query = "select * from `" + AlumnoContract.TABLE_NAME 
				+ "` where `" + AlumnoContract.DNI + "`=?";
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			ps.setString(1, dni);
		 ResultSet rs = ps.executeQuery();

		 while(rs.next()){
			 String dniStr = rs.getString(AlumnoContract.DNI);
			 String nombre = rs.getString(AlumnoContract.NOMBRE);
			 String apellidos = rs.getString(AlumnoContract.APELLIDOS);
			 Date date = new Date(rs.getLong(AlumnoContract.FECHA_NACIMIENTO));
			 alumno = new Alumno(dniStr, nombre, apellidos, date);
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return alumno;
	}

	@Override
	public boolean update(Alumno alumno) {
		boolean resultado = false;
		String query = "update `" + AlumnoContract.TABLE_NAME + "` set `"
				+ AlumnoContract.NOMBRE + "`=?,`"
				+ AlumnoContract.APELLIDOS + "`=?,`"
				+ AlumnoContract.FECHA_NACIMIENTO + "`=?"
				+ " where `"
				+ AlumnoContract.DNI + "`=?" ;
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			System.out.println(query);
			ps.setString(1, alumno.getNombre());
			ps.setString(2, alumno.getApellidos());
			ps.setLong(3, alumno.getFechaNacimiento().getTime());
			ps.setString(4, alumno.getDni());
			resultado = (ps.executeUpdate() == 1);
		}catch (SQLException e) {
			e.printStackTrace();
		}

		 
		return resultado;
	}

	@Override
	public boolean delete(String dni) {
		boolean resultado = false;
		String query = "delete from `" + AlumnoContract.TABLE_NAME
				+ "` where `"
				+ AlumnoContract.DNI + "`=?" ;
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			ps.setString(1, dni);
			resultado = (ps.executeUpdate() == 1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return resultado;
	}

	@Override
	public ArrayList<Alumno> findAll() {
		ArrayList <Alumno> alumnosList = new ArrayList<>();
		String query = "select * from " + AlumnoContract.TABLE_NAME;
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			
		 ResultSet rs = ps.executeQuery();

		 while(rs.next()){
			 String dni = rs.getString(AlumnoContract.DNI);
			 String nombre = rs.getString(AlumnoContract.NOMBRE);
			 String apellidos = rs.getString(AlumnoContract.APELLIDOS);
			 Date date = new Date(rs.getLong(AlumnoContract.FECHA_NACIMIENTO));
			 alumnosList.add(new Alumno(dni, nombre, apellidos, date));
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return alumnosList;
	}
	
	public ArrayList<Alumno> findByNombre(String nombre) {
		ArrayList <Alumno> alumnosList = new ArrayList<>();
		String query = "select * from `" + AlumnoContract.TABLE_NAME 
				+ "` where `" + AlumnoContract.NOMBRE + "`=?";
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			ps.setString(1, nombre);
		 ResultSet rs = ps.executeQuery();

		 while(rs.next()){
			 String dni = rs.getString(AlumnoContract.DNI);
			 String nombreStr = rs.getString(AlumnoContract.NOMBRE);
			 String apellidos = rs.getString(AlumnoContract.APELLIDOS);
			 Date date = new Date(rs.getLong(AlumnoContract.FECHA_NACIMIENTO));
			 alumnosList.add(new Alumno(dni, nombreStr, apellidos, date));
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return alumnosList;
	}
		
}
