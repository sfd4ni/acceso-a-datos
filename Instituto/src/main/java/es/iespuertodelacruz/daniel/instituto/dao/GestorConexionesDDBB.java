package es.iespuertodelacruz.daniel.instituto.dao;

import org.apache.commons.dbcp.BasicDataSource;


import java.sql.Connection;
import java.sql.SQLException;

public class GestorConexionesDDBB {
	
   BasicDataSource basicDataSource;
   String jdbcUrl;
   String usuario;
   String clave;
    
    public GestorConexionesDDBB(String ddbb, String nombreUsuario, String password) {
        jdbcUrl = "jdbc:mysql://localhost/" + ddbb + "?serverTimezone=UTC";
        usuario = nombreUsuario;
        clave = password;
        cargarDriverMysql();
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl(jdbcUrl);
        basicDataSource.setUsername(nombreUsuario);
        basicDataSource.setPassword(password);
    }
    
    private static void cargarDriverMysql(){
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 } catch(ClassNotFoundException ex) {
			 System.err.println("no carga el driver");
			 System.exit(1);
		 }
	 }
    
	public Connection getConnection() {
        
        Connection con=null;
        try {
            con = basicDataSource.getConnection();
        } catch (SQLException ex) {
            System.exit(1);
        }        
        return con;
    }
}
