/**
 * 
 */
package ec.edu.ups.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Orlando Real
 *
 */
public class ContextJDBC {

	Connection conexion = null;
	Statement sentencia = null;
	ResultSet result = null;
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/agetelfdb";
	private static final String USER = "root";
	private static final String PASS = "root";
	private static ContextJDBC jdbc1 = null;
	private static ContextJDBC jdbc2 = null;
	
	public ContextJDBC() {
		this.connect();
	}
	
	public void connect() {
		try {
			Class.forName(DRIVER);
			this.conexion = DriverManager.getConnection(URL, USER, PASS);
			this.sentencia = conexion.createStatement();
		} catch (ClassNotFoundException e){
			System.err.println("Imposible cargar el driver " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Imposible conectar " + e.getMessage());
		}
	}
	
	
	/**
	 * Método query.
	 * 
	 * Realiza una sentencia SELECT a la base de datos.
	 */
	public ResultSet query(String sql) {
		try {
			return this.sentencia.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBC:query): ---" + sql + "---" + e);
		}
		return null;
	}
	
	
	/**
	 * Método update.
	 * 
	 * Realiza una sentencia INSERT, UDPDATE, DELETE, CREATE, entre otras a la base
	 * de datos.
	 */
	public boolean update(String sql) {
		try {
			this.sentencia.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBC:update)... actualizacion: ---" + sql + "---" + e);
			return false;
		}
	}
	
	
	/**
	 * Método getJDBC.
	 * 
	 * Obtiene una conexión activa a la base de datos
	 * 
	 * @return jdbc
	 */
	protected static ContextJDBC getJDBC1() {
		// creación de la conexión a la base de datos solo si no ha sido creada patrón
		// de diseño singleton
		if (jdbc1 == null) {
			jdbc1 = new ContextJDBC();
		}
		return jdbc1;
	}
	
	/**
	 * Método getJDBC.
	 * 
	 * Obtiene una conexión activa a la base de datos
	 * 
	 * @return jdbc
	 */
	protected static ContextJDBC getJDBC2() {
		// creación de la conexión a la base de datos solo si no ha sido creada patrón
		// de diseño singleton
		if (jdbc2 == null) {
			jdbc2 = new ContextJDBC();
		}
		return jdbc1;
	}
	
	/*
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
*/
}
