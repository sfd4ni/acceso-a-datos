package es.iespuertodelacruz.daniel.instituto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertodelacruz.daniel.instituto.contracts.AsignaturaContract;
import es.iespuertodelacruz.daniel.instituto.contracts.MatriculaContract;
import es.iespuertodelacruz.daniel.instituto.modelo.Alumno;
import es.iespuertodelacruz.daniel.instituto.modelo.Asignatura;
import es.iespuertodelacruz.daniel.instituto.modelo.Matricula;

public class MatriculaDAO implements Crud<Matricula, Integer> {

	GestorConexionesDDBB gc;
	
	public MatriculaDAO(GestorConexionesDDBB gc) {
		this.gc = gc;
	}
	@Override
	public Matricula save(Matricula dao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matricula findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Matricula dao) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	@Override
	public ArrayList<Matricula> findAll() {
		AlumnoDAO alumnoDAO = new AlumnoDAO(gc);
		AsignaturaDAO asignaturaDAO = new AsignaturaDAO(gc);
		
		ArrayList <Matricula> matriculasList = new ArrayList<>();
		String queryMatriculas = "select * from " + MatriculaContract.TABLE_NAME;
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(queryMatriculas);
				 ){
			
		 ResultSet rs = ps.executeQuery();

		 while(rs.next()){
			 int id = rs.getInt(MatriculaContract._ID);
			 String dni = rs.getString(MatriculaContract.DNI);
			 int year = rs.getInt(MatriculaContract.YEAR);
			 Alumno alumno = alumnoDAO.findById(dni);
			 
			 ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();
			 
			 listaAsignaturas = asignaturaDAO.findByIdMatricula(id);
			 
			 Matricula matricula = new Matricula(year);
			 matricula.setIdmatricula(id);
			 matricula.setAsignaturas(listaAsignaturas);
			 matricula.setAlumno(alumno);
			 matricula.setIdmatricula(id);
			 
			 matriculasList.add(matricula);
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return matriculasList;
	}

}
