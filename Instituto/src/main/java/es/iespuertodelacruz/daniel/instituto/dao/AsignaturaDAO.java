package es.iespuertodelacruz.daniel.instituto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import es.iespuertodelacruz.daniel.instituto.contracts.AlumnoContract;
import es.iespuertodelacruz.daniel.instituto.contracts.AsignaturaContract;
import es.iespuertodelacruz.daniel.instituto.modelo.Alumno;
import es.iespuertodelacruz.daniel.instituto.modelo.Asignatura;

public class AsignaturaDAO implements Crud<Asignatura, Integer>{

	GestorConexionesDDBB gc;
	public AsignaturaDAO(GestorConexionesDDBB gc) {
		this.gc = gc;
	}
	@Override
	public Asignatura save(Asignatura asignatura) {
		Asignatura resultado = null;
		String query="INSERT INTO `" + AsignaturaContract.TABLE_NAME + "` "
				+ "(`" + AsignaturaContract.NOMBRE + "`, `" + AsignaturaContract.CURSO + "`)"
				+ " VALUES (?, ?)";
		try (
		Connection cn = gc.getConnection();
		 PreparedStatement ps = cn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		 ){
			 ps.setString(1, asignatura.getNombre());
			 ps.setString(2, asignatura.getCurso());
			 ps.executeUpdate();
			 ResultSet rs = ps.getGeneratedKeys();
			 while(rs.next()){
				 int id = rs.getInt(1);
				 asignatura.setIdasignatura(id);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return resultado;
	}

	@Override
	public Asignatura findById(Integer id) {
		Asignatura asignatura = null;
		String query = "select * from `" + AsignaturaContract.TABLE_NAME 
				+ "` where `" + AsignaturaContract._ID + "`=?";
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int idAsignatura = rs.getInt(AsignaturaContract._ID);
				String nombre = rs.getString(AsignaturaContract.NOMBRE);
				String curso = rs.getString(AsignaturaContract.CURSO);
				asignatura = new Asignatura(nombre, curso);
				asignatura.setIdasignatura(idAsignatura);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return asignatura;
	}
	
	public Asignatura findByName(String name) {
		Asignatura asignatura = null;
		String query = "select * from `" + AsignaturaContract.TABLE_NAME 
				+ "` where `" + AsignaturaContract.NOMBRE + "`=?";
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int idAsignatura = rs.getInt(AsignaturaContract._ID);
				String nombre = rs.getString(AsignaturaContract.NOMBRE);
				String curso = rs.getString(AsignaturaContract.CURSO);
				asignatura = new Asignatura(nombre, curso);
				asignatura.setIdasignatura(idAsignatura);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return asignatura;
	}

	@Override
	public boolean update(Asignatura asignatura) {
		boolean resultado = false;
		String query = "update `" + AsignaturaContract.TABLE_NAME + "` set `"
				+ AsignaturaContract.NOMBRE + "`=?,`"
				+ AsignaturaContract.CURSO + "`=?"
				+ " where `"
				+ AsignaturaContract._ID + "`=?" ;
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			ps.setString(1, asignatura.getNombre());
			ps.setString(2, asignatura.getCurso());
			ps.setInt(3, asignatura.getIdasignatura());
			resultado = (ps.executeUpdate() == 1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean delete(Integer id) {
		boolean resultado = false;
		String query = "delete from `" + AsignaturaContract.TABLE_NAME
				+ "` where `"
				+ AsignaturaContract._ID + "`=?" ;
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			ps.setInt(1, id);
			resultado = (ps.executeUpdate() == 1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return resultado;
	}

	@Override
	public ArrayList<Asignatura> findAll() {
		ArrayList <Asignatura> asignaturasList = new ArrayList<>();
		String query = "select * from " + AsignaturaContract.TABLE_NAME;
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(query);
				 ){
			
		 ResultSet rs = ps.executeQuery();

		 while(rs.next()){
			 int id = rs.getInt(AsignaturaContract._ID);
			 String nombre = rs.getString(AsignaturaContract.NOMBRE);
			 String curso = rs.getString(AsignaturaContract.CURSO);
			 Asignatura asignatura = new Asignatura(nombre, curso);
			 asignatura.setIdasignatura(id);
			 asignaturasList.add(asignatura);
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return asignaturasList;
	}

}
