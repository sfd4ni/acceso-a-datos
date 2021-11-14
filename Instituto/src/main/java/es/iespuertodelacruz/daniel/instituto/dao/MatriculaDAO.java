package es.iespuertodelacruz.daniel.instituto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertodelacruz.daniel.instituto.contracts.AsignaturaContract;
import es.iespuertodelacruz.daniel.instituto.contracts.AsignaturaMatriculaContract;
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
		boolean ok = false;
		AsignaturaDAO asignaturaDao = new AsignaturaDAO(this.gc);
		String queryInsertMatr = "INSERT INTO " + MatriculaContract.TABLE_NAME + " (`"+ MatriculaContract.DNI + "`, `" 
				+ MatriculaContract.YEAR + "`) VALUES(?,?) " ;
		String queryInsert = "INSERT INTO " + AsignaturaMatriculaContract.TABLE_NAME 
				+ "(`" + AsignaturaMatriculaContract.ID_ASIGNATURA + "`,`"
				+ AsignaturaMatriculaContract.ID_MATRICULA + "`) VALUES(?,?) " ;
		try (
			Connection cn = gc.getConnection();
	
			PreparedStatement psInsertMatr = cn.prepareStatement(queryInsertMatr, PreparedStatement.RETURN_GENERATED_KEYS);
			PreparedStatement psInsert = cn.prepareStatement(queryInsert);
		){
			cn.setAutoCommit(false);
			psInsertMatr.setString(1, dao.getAlumno().getDni());
			psInsertMatr.setInt(2, dao.getYear());
			
			int respuesta = psInsertMatr.executeUpdate();
			
			ResultSet rs = psInsertMatr.getGeneratedKeys();
			 while(rs.next()){
				 int id = rs.getInt(1);
				 dao.setIdmatricula(id);
			 }
			
			for (Asignatura asignatura : dao.getAsignaturas()) {
				psInsert.setInt(1, asignatura.getIdasignatura());
				psInsert.setInt(2, dao.getIdmatricula());
				respuesta = respuesta * psInsert.executeUpdate();
			}
			
			
			ok = respuesta > 0; 
			if(ok) {
				cn.commit();
				cn.setAutoCommit(true);	
			} else{
				 cn.rollback();
				 cn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao;
	}


	public Matricula findByDni(String dni) {
		AlumnoDAO alumnoDAO = new AlumnoDAO(gc);
		AsignaturaDAO asignaturaDAO = new AsignaturaDAO(gc);
		ArrayList<Asignatura> listaAsignaturas = null;
		Matricula matricula = null;
		
		String queryMatriculas = "select * from " + MatriculaContract.TABLE_NAME + " where `" + MatriculaContract.DNI + "`=?";
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(queryMatriculas);
				 ){
		 ps.setString(1, dni);
		 ResultSet rs = ps.executeQuery();

		 while(rs.next()){
			 int id = rs.getInt(MatriculaContract._ID);
			 String dniStr = rs.getString(MatriculaContract.DNI);
			 int year = rs.getInt(MatriculaContract.YEAR);
			 Alumno alumno = alumnoDAO.findById(dniStr);
			 
			 listaAsignaturas = asignaturaDAO.findByIdMatricula(id);
			 
			 matricula = new Matricula(year);
			 matricula.setIdmatricula(id);
			 matricula.setAsignaturas(listaAsignaturas);
			 matricula.setAlumno(alumno);
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return matricula;
	}
	
	
	public ArrayList<Matricula> findByYear(Integer year) {
		AlumnoDAO alumnoDAO = new AlumnoDAO(gc);
		AsignaturaDAO asignaturaDAO = new AsignaturaDAO(gc);
		ArrayList<Asignatura> listaAsignaturas = null;
		ArrayList<Matricula> listaMatriculas = null;
		Matricula matricula = null;
		
		String queryMatriculas = "select * from " + MatriculaContract.TABLE_NAME + " where `" + MatriculaContract.YEAR + "`=?";
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement ps = cn.prepareStatement(queryMatriculas);
				 ){
		 ps.setInt(1, year);
		 ResultSet rs = ps.executeQuery();
		 listaMatriculas = new ArrayList<Matricula>();
		 while(rs.next()){
			 int id = rs.getInt(MatriculaContract._ID);
			 String dni = rs.getString(MatriculaContract.DNI);
			 int yearInt = rs.getInt(MatriculaContract.YEAR);
			 Alumno alumno = alumnoDAO.findById(dni);
			 
			 listaAsignaturas = asignaturaDAO.findByIdMatricula(id);
			 matricula = new Matricula(yearInt);
			 matricula.setIdmatricula(id);
			 matricula.setAsignaturas(listaAsignaturas);
			 matricula.setAlumno(alumno);
			 
			 listaMatriculas.add(matricula);
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return listaMatriculas;
	}

	@Override
	public boolean update(Matricula dao) {
		boolean ok = false;
		AsignaturaDAO asignaturaDao = new AsignaturaDAO(this.gc);
		String queryDelete = "DELETE FROM "+ AsignaturaMatriculaContract.TABLE_NAME + " WHERE "
				+ AsignaturaMatriculaContract.ID_MATRICULA + " = ? " ;
		
		String queryUpdate = "UPDATE "+ MatriculaContract.TABLE_NAME + " SET "+ MatriculaContract.DNI + " = ?, "
					+ MatriculaContract.YEAR + "= ? WHERE "+ MatriculaContract._ID + " = ? " ;
		
		String queryInsert = "INSERT INTO " + AsignaturaMatriculaContract.TABLE_NAME 
				+ "(`" + AsignaturaMatriculaContract.ID_ASIGNATURA + "`,`"
				+ AsignaturaMatriculaContract.ID_MATRICULA + "`) VALUES(?,?) " ;
		try (
			Connection cn = gc.getConnection();
	
			PreparedStatement psDelete = cn.prepareStatement(queryDelete);
			PreparedStatement psUpdate = cn.prepareStatement(queryUpdate);
			PreparedStatement psInsert = cn.prepareStatement(queryInsert);
		){
			cn.setAutoCommit(false);
			psDelete.setInt(1, dao.getIdmatricula());
			int respuesta = psDelete.executeUpdate();
			psUpdate.setString(1, dao.getAlumno().getDni());
			psUpdate.setInt(2, dao.getYear());
			psUpdate.setInt(3, dao.getIdmatricula());
			
			respuesta = psUpdate.executeUpdate();
			
			for (Asignatura asignatura : dao.getAsignaturas()) {
				psInsert.setInt(1, asignatura.getIdasignatura());
				psInsert.setInt(2, dao.getIdmatricula());
				respuesta = respuesta * psInsert.executeUpdate();
			}
			ok = respuesta > 0; 
			if(ok) {
				cn.commit();
				cn.setAutoCommit(true);	
			} else{
				 cn.rollback();
				 cn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public boolean delete(Integer id) {
		boolean resultado = false;
		String queryDeleteAsigMatr = "delete from `" + AsignaturaMatriculaContract.TABLE_NAME
				+ "` where `"
				+ AsignaturaMatriculaContract.ID_MATRICULA + "`=?" ;
		String queryDeleteMatricula = "delete from `" + MatriculaContract.TABLE_NAME
				+ "` where `"
				+ MatriculaContract._ID + "`=?";
		try (
				Connection cn = gc.getConnection();
				 PreparedStatement psDeleteAsigMatr = cn.prepareStatement(queryDeleteAsigMatr);
				PreparedStatement psDeleteMatricula = cn.prepareStatement(queryDeleteMatricula);
				 ){
			cn.setAutoCommit(false);
			
			psDeleteAsigMatr.setInt(1, id);
			psDeleteAsigMatr.executeUpdate();
			psDeleteMatricula.setInt(1, id);
			resultado = (psDeleteMatricula.executeUpdate() == 1);
			if(resultado) {
				cn.commit();
				cn.setAutoCommit(true);
			} else{
				 cn.rollback();
				 cn.setAutoCommit(true);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return resultado;
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
			 
			 matriculasList.add(matricula);
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return matriculasList;
	}
	@Override
	public Matricula findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
