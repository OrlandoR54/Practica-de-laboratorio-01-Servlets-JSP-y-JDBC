package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;

public class JDBCUsuarioDAO extends JDBCGenericDAO<Usuario, String> implements UsuarioDAO {

	@Override
	public void createTable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(Usuario user) {
		// TODO Auto-generated method stub
		conexionUno.update("INSERT Usuario VALUES (" + user.getCedula() + ", " + user.getNombre() + ", '" + user.getApellido()
				+ ", " + user.getCorreo() + ", " + user.getPassword() + "')");
	}

	@Override
	public Usuario read(String cedula) {
		// TODO Auto-generated method stub
		Usuario user = null;
		ResultSet rs = conexionUno.query("SELECT * FROM Usuario WHERE usu_cedula=" + cedula);
		try {
			if (rs != null && rs.next()) {
				user = new Usuario(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("correo"), rs.getString("password"), null);
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDAO:read): " + e.getMessage());
		}
		if (user == null) {
			return null;
		} 
		return user;
	}

	@Override
	public void update(Usuario user) {
		// TODO Auto-generated method stub

		System.out.println("Act:..." + user);
		conexionUno.update("UPDATE Usuario SET usu_nombre = '" + user.getNombre() + "', usu_contrasena = '" + user.getPassword()
				+ "', usu_apellido= " + user.getApellido() + "', usu_correo = '" + user.getCorreo() + " WHERE usu_cedula = " + user.getCedula());
	}

	@Override
	public void delete(Usuario user) {
		// TODO Auto-generated method stub
		conexionUno.update("DELETE FROM Usuario WHERE usu_cedula = " + user.getCedula());
	}

	@Override
	public List<Usuario> find() {
		// TODO Auto-generated method stub
		List<Usuario> list = new ArrayList<Usuario>();
		ResultSet rs = conexionUno.query("SELECT * FROM Usuario");
		try {
			while (rs.next()) {
				Usuario user = new Usuario(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("correo") , rs.getString("password"), null);
				list.add(user);

			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDAO:find): " + e.getMessage());
		}
		
		return list;
	}

}
