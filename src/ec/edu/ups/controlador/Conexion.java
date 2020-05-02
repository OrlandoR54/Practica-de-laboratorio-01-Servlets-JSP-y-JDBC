package ec.edu.ups.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conexion = null;
		Statement sentencia = null;
		ResultSet result = null;
		
		String url = "jdbc:mysql://localhost:3306/jee";
		String user = "root";
		String pass = "root";
		System.out.println("Conecta " + url + user + pass);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, pass);
			
			sentencia = conexion.createStatement();
		} catch (ClassNotFoundException e){
			System.err.println("Imposible cargar el driver " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Imposible conectar " + e.getMessage());
		}
		
		try {
			sentencia.executeUpdate("DROP TABLE IF EXISTS tabla1");
			sentencia.executeUpdate("CREATE TABLE tabla1 (id1 INT PRIMARY KEY, nombre CHAR(20) DEFAULT '-')");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Creacion de tabla fallida" + e.getMessage());
		}
		try {
			sentencia.executeUpdate("INSERT tabla1 (id1) VALUES (3)");
			sentencia.executeUpdate("INSERT tabla1 VALUES (4,'Jesus')");
			sentencia.executeUpdate("INSERT tabla1 VALUES (5,'Juan')");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("INteraccion de datos de tabla fallida: " +e.getMessage());
		}
		try {
			result = sentencia.executeQuery("SELECT * FROM tabla1");
			while (result.next()) {
				System.out.println("id1: " + result.getLong("id1") + ", nombre: " + result.getString("nombre"));
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Consulta Fallida: " + e.getMessage());
		}
	}

}
