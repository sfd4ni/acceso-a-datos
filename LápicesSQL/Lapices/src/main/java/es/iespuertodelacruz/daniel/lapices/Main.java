package es.iespuertodelacruz.daniel.lapices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	 private static void cargarDriverMysql(){
	 try {
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 } catch(ClassNotFoundException ex) {
	 System.err.println("no carga el driver");
	 System.exit(1);
	 }
	 }

	 public static void main(String[] args) {

	 cargarDriverMysql();
	 Connection conexion = null;
	 try {
	 conexion = DriverManager.getConnection(
	 "jdbc:mysql://localhost/oficina?serverTimezone=UTC","root",
	"1q2w3e4r");

	 Statement s = conexion.createStatement();
	 String sql = "select * from lapices";
	 ResultSet rs = s.executeQuery(sql);

	 while(rs.next()){

	 int id = rs.getInt("idlapiz");
	 String marca = rs.getString("marca");
	 int numero = rs.getInt("numero");

	 System.out.println("Lápiz: " + id + " " + marca + " " + numero);

	 }
	 s.close();
	conexion.close();

	 } catch (SQLException ex) { ex.printStackTrace(); }
	 }
	}
