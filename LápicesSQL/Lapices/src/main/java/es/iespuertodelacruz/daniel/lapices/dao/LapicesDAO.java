package es.iespuertodelacruz.daniel.lapices.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertodelacruz.daniel.lapices.modelo.Lapiz;

public class LapicesDAO {
	public LapicesDAO() {}
	private static void cargarDriverMysql(){
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 } catch(ClassNotFoundException ex) {
			 System.err.println("no carga el driver");
			 System.exit(1);
		 }
	 }
	public ArrayList<Lapiz> buscarPorMarca(String marca) {
		 cargarDriverMysql();
		 ArrayList <Lapiz> lapicesList = new ArrayList<>();
		 Connection conexion = null;
		 try {
		 conexion = DriverManager.getConnection(
		 "jdbc:mysql://localhost/oficina?serverTimezone=UTC","root",
		null);

		 Statement s = conexion.createStatement();
		 String sql = "select * from lapices where marca='" + marca + "'";
		 ResultSet rs = s.executeQuery(sql);

		 while(rs.next()){
			 int id = rs.getInt("idlapiz");
			 String marcaString = rs.getString("marca");
			 int numero = rs.getInt("numero");
			 lapicesList.add(new Lapiz(id, marcaString, numero));
		 }
		 s.close();
		 conexion.close();
		 } catch (SQLException ex) { ex.printStackTrace(); }
		 return lapicesList;
	}
}
