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

	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/agetelfdb";
	private static final String USER = "root";
	private static final String PASS = "root";
	private static ContextJDBC jdbc1 = null;
	private static ContextJDBC jdbc2 = null;
	private Statement statement = null;
	
	public ContextJDBC() {
		this.connect();
	}
	
	public void connect() {
		System.out.println("Muestra " + DRIVER);
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conectando " + connection);
			this.statement = connection.createStatement();
			System.out.println("Se conecto");
		} catch (ClassNotFoundException e){
			System.out.println(">>>WARNING (JDBC:connect)...problemas con el driver: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Imposible conectar " + e.getMessage());
		}
	}
	
	
	/**
	 * Método query.
	 * 
	 * Realiza una sentencia SELECT a la base de datos.
	 */
	public ResultSet query(String sql) {
		try {
			System.out.println("Prueba con contextJDBC, 'SELECT': " + sql);
			return this.statement.executeQuery(sql);
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
			System.out.println("Prueba con contextJDBC, 'Insert'" + sql);
			this.statement.executeUpdate(sql);
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
		return jdbc2;
	}
	
}
