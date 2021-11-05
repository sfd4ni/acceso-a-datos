package es.iespuertodelacruz.daniel.lapices.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.daniel.lapices.modelo.Lapiz;

public class GestorLapices {
private String nombreBD;
	
	public GestorLapices(String nombreBD) {
		this.nombreBD = nombreBD;
		cargarDriverMysql();
	}
	
	private static void cargarDriverMysql(){
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 } catch(ClassNotFoundException ex) {
			 System.err.println("no carga el driver");
			 System.exit(1);
		 }
	 }
	public boolean buscarPorId(int id) {
		 Lapiz lapiz = new Lapiz();
		 Connection conexion = null;
		 try {
		 conexion = DriverManager.getConnection(
		 "jdbc:mysql://localhost/" + this.nombreBD + "?serverTimezone=UTC","root",
		null);

		 Statement s = conexion.createStatement();
		 String sql = "select * from lapices where idlapiz='" + id + "'";
		 ResultSet rs = s.executeQuery(sql);

		 if(rs.next()){
			 return true;
		 }
		 s.close();
		 conexion.close();
		 } catch (SQLException ex) { ex.printStackTrace(); }
		 return false;
	}
	public ArrayList<Lapiz> buscarPorMarca(String marca) {
		 ArrayList <Lapiz> lapicesList = new ArrayList<>();
		 Connection conexion = null;
		 try {
		 conexion = DriverManager.getConnection(
		 "jdbc:mysql://localhost/" + this.nombreBD + "?serverTimezone=UTC","root",
		null);

		 String sql = "select * from lapices where marca = ?";
		 PreparedStatement ps = conexion.prepareStatement(sql);
		 ps.setString(1, marca);
		 ResultSet rs = ps.executeQuery();

		 while(rs.next()){
			 int id = rs.getInt("idlapiz");
			 String marcaString = rs.getString("marca");
			 int numero = rs.getInt("numero");
			 lapicesList.add(new Lapiz(id, marcaString, numero));
		 }
		 ps.close();
		 conexion.close();
		 } catch (SQLException ex) { ex.printStackTrace(); }
		 return lapicesList;
	}
	
	public boolean actualizarLapiz(Lapiz lapiz, int idLapiz) {
		Connection conexion = null;
		boolean status = false;
		 try {
			 conexion = DriverManager.getConnection(
			 "jdbc:mysql://localhost/" + this.nombreBD + "?serverTimezone=UTC","root",
			null);
			 String query = "Update lapices Set idlapiz=?, marca=?, numero=? Where idlapiz=?"; 
			 PreparedStatement ps = conexion.prepareStatement(query);
			 ps.setInt(1, lapiz.getId());
			 ps.setString(2, lapiz.getMarca());
			 ps.setInt(3, lapiz.getNumero());
			 ps.setInt(4, idLapiz);
			 status = ps.execute();
		 } catch (SQLException ex) {
			 ex.printStackTrace();
			 }
		 return status;
	}
	public boolean borrarLapiz(int idLapiz) {
		Connection conexion = null;
		boolean status = false;
		 try {
			 conexion = DriverManager.getConnection(
			 "jdbc:mysql://localhost/" + this.nombreBD + "?serverTimezone=UTC","root",
			null);
			 String query = "DELETE FROM lapices WHERE idlapiz=?";
			 PreparedStatement ps = conexion.prepareStatement(query);
			 ps.setInt(1, idLapiz);
			 status = ps.execute();
		 } catch (SQLException ex) {
			 ex.printStackTrace();
			 }
		 return status;
	}
	
	public Lapiz saveLapiz(Lapiz lapiz) throws SQLException{

		 Lapiz resultado = new Lapiz();
		 Connection conexion = DriverManager.getConnection(
				 "jdbc:mysql://localhost/" + this.nombreBD + "?serverTimezone=UTC","root",
					null);

		 if (!buscarPorId(lapiz.getId())) {
			 String sql = "INSERT INTO lapices (idlapiz, marca, numero) VALUES(?,?,?)";
			 PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			 ps.setInt(1, lapiz.getId());
			 ps.setString(2, lapiz.getMarca());
			 ps.setInt(3, lapiz.getNumero());
			 ps.executeUpdate();
			 ResultSet rs = ps.getGeneratedKeys();
			 while(rs.next()){
				 int id = rs.getInt(1);
				 resultado.setId(id);
				 resultado.setMarca(lapiz.getMarca());
				 resultado.setNumero(lapiz.getNumero());
			 }
			 ps.close();
			 conexion.close();
		 }
		 return resultado;
	 }

}
