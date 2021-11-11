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

public class AsignaturaDAO implements Crud<Asignatura, String>{

	GestorConexionesDDBB gc;
	public AsignaturaDAO(GestorConexionesDDBB gc) {
		this.gc = gc;
	}
	@Override
	public Asignatura save(Asignatura asignatura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asignatura findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Asignatura asignatura) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
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
