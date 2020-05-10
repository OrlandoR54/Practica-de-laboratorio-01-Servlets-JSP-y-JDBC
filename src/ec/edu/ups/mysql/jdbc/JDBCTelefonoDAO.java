/**
 * 
 */
package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;


/**
 * @author Orlando Real
 *
 */
/**
 * @author Orlando Real
 *
 */
public class JDBCTelefonoDAO extends JDBCGenericDAO<Telefono, String> implements TelefonoDAO{

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		conexionDos.update("CREATE TABLE IF NOT EXISTS telefono ("
                + "	tel_codigo INT NOT NULL AUTO_INCREMENT,"
                + "	tel_numero VARCHAR(20),"
                + "	tel_tipo  VARCHAR(50),"
                + "	tel_operadora VARCHAR(50),"
                + "	usu_cedula VARCHAR(10) NOT NULL,"
                + "	PRIMARY KEY (tel_codigo),"
                + "	FOREIGN KEY (usu_cedula) REFERENCES usuario(usu_cedula)"
                + ");");
	}

	/***
	 * Se inserta el telefono con la clave foranea incluida, la cual es el numero de cedula del usuario
	 */
	@Override
	public boolean create(Telefono telefono) {
		// TODO Auto-generated method stub
		return conexionUno.update("INSERT INTO Telefono  (tel_numero, tel_tipo, tel_operadora, usu_cedula)" + 
				"VALUES ( '" + telefono.getNumero() + "', '"+ telefono.getTipo() + "', '" + telefono.getOperadora() +"','"+telefono.getUsuario().getCedula() + "');");
	}

	@Override
	public Telefono read(String numero) {
		// TODO Auto-generated method stub
		Telefono telefono = null;
		ResultSet rsTelefono = conexionUno.query("SELECT * FROM Telefono WHERE tel_numero=" + numero);
		try {
			if (rsTelefono != null && rsTelefono.next()) {
				telefono = new Telefono(rsTelefono.getInt("tel_codigo"), rsTelefono.getString("tel_numero"), rsTelefono.getString("tel_tipo"), rsTelefono.getString("tel_operadora"));
				ResultSet rsUsuario=conexionDos.query("SELECT * FROM usario WHERE usu_cedula='"+rsTelefono.getString("usu_cedula")+"');");
				if (rsUsuario !=null && rsUsuario.next()) {
					Usuario usuario = new Usuario();
					usuario.setCedula(rsUsuario.getString("usu_cedula"));
					usuario.setNombre(rsUsuario.getString("usu_nombre"));
					usuario.setApellido(rsUsuario.getString("usu_apellido"));
					usuario.setCorreo(rsUsuario.getString("usu_correo"));
					usuario.setPassword(rsUsuario.getString("usu_contrasena"));
					telefono.setUsuario(usuario);
				}

			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCTelefonoDAO:read): " + e.getMessage());
		}
		if (telefono == null) {
			return null;
		}
		return telefono;
	}
	
	@Override
	public void update(Telefono telefono) {
		// TODO Auto-generated method stub
			conexionUno.update("UPDATE telefono SET tel_numero = '" + telefono.getNumero() + "', tel_tipo = '" + telefono.getTipo()+ "',"
					+ " tel_operadora= '" +telefono.getOperadora()+"' WHERE tel_codigo = " + telefono.getId());
	}

	/**
	 * Se eliminara un telefono especificando el numero de cedula del usuario
	 */
	@Override
	public void delete(Telefono telefono) {
		// TODO Auto-generated method stub
		conexionUno.update("DELETE FROM telefono  WHERE tel_codigo = " + telefono.getId());
	}

	/**
	 * Busca el telefono por la cedula del usuario
	 * 
	 **/
	@Override
	public Set<Telefono> findByUserId(String cedulaUser) {
		// TODO Auto-generated method stub
		Set<Telefono> list = new HashSet<Telefono>();
		ResultSet rsTelefono = conexionUno.query("SELECT * FROM telefono WHERE usu_cedula='" + cedulaUser + "';");
		try {
			while (rsTelefono.next()) {
				Telefono telefono = new Telefono(rsTelefono.getInt("tel_codigo"),rsTelefono.getString("tel_numero"), rsTelefono.getString("tel_tipo"),
						rsTelefono.getString("tel_operadora"));
//				
//				ResultSet rsUsuario = conexionDos.query("SELECT * FROM usuario WHERE usu_cedula='" + rsTelefono.getString("usu_cedula") + "';");
//				if (rsUsuario != null && rsUsuario.next()) {
//					Usuario usuario = new Usuario();
//					usuario.setCedula(rsUsuario.getString("usu_cedula"));
//					usuario.setNombre(rsUsuario.getString("usu_nombre"));
//					usuario.setApellido(rsUsuario.getString("usu_apellido"));
//					usuario.setCorreo(rsUsuario.getString("usu_correo"));
//					usuario.setContrasenia(rsUsuario.getString("usu_contrasenia"));
//					telefono.setUsuario(usuario);
//
//				}
				list.add(telefono);
			}
		} catch (SQLException e) {
			System.out.println("ERROR JDBCTelefonoDAO en el metodo findbyPersonaCed "+ e.getMessage());
		}

		return list;
	}

	/**
	 * Busca todos los telefonos de la Base de Datos
	 */
	@Override
	public List<Telefono> find() {
		List<Telefono> list=new ArrayList<Telefono>();
		ResultSet rsTelefono = conexionUno.query("SELECT * FROM telefono;");
		try {
			while (rsTelefono.next()) {
				Telefono telefono = new Telefono(rsTelefono.getInt("tel_codigo"),rsTelefono.getString("tel_numero"), rsTelefono.getString("tel_tipo"),
						rsTelefono.getString("tel_operadora"));
				ResultSet rsUsuario = conexionDos.query("SELECT * FROM usuario WHERE usu_cedula='" + rsTelefono.getString("usu_cedula") + "';");
				if (rsUsuario != null && rsUsuario.next()) {
					Usuario usuario = new Usuario();
					usuario.setCedula(rsUsuario.getString("usu_cedula"));
					usuario.setNombre(rsUsuario.getString("usu_nombre"));
					usuario.setApellido(rsUsuario.getString("usu_apellido"));
					usuario.setCorreo(rsUsuario.getString("usu_correo"));
					usuario.setPassword(rsUsuario.getString("usu_contrasena"));
					telefono.setUsuario(usuario);

				}
				list.add(telefono);
			}
		} catch (SQLException e) {
			System.out.println("ERROR JDBCTelefonoDAO en el metodo List<Telefono> find() "+e.getMessage());
		}
		return list;
	}
	
	/**
	 * Busca el telefono por id
	 **/
	@Override
	public Telefono findbyTelefonoId(int tel_codigo) {
		// TODO Auto-generated method stub
		Telefono telefono = null;
		ResultSet rsTelefono = conexionUno.query("SELECT * FROM telefono WHERE tel_codigo=" + tel_codigo + ";");
		try {
			if (rsTelefono != null && rsTelefono.next()) {
				telefono = new Telefono(rsTelefono.getInt("tel_codigo"), rsTelefono.getString("tel_numero"),
						rsTelefono.getString("tel_tipo"), rsTelefono.getString("tel_operadora"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return telefono;
	}

	
	
	
	
}
