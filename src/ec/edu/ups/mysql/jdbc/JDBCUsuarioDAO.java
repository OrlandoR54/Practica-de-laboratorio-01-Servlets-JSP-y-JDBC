package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;

public class JDBCUsuarioDAO extends JDBCGenericDAO<Usuario, String> implements UsuarioDAO {

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		conexionUno.update("CREATE TABLE IF NOT EXISTS usuario (" + "	usu_cedula VARCHAR(10) NOT NULL,"
				+ "	usu_nombre VARCHAR(50)," + "	usu_apellido VARCHAR(50)," + "	usu_correo VARCHAR(100),"
				+ "	usu_pass VARCHAR(255)," + "	PRIMARY KEY (usu_cedula)" + ");");
	}

	/***
	 * Se inserta el nuevo usuario
	 * 
	 * @return
	 */
	@Override
	public boolean create(Usuario user) {
		// TODO Auto-generated method stub
		return conexionUno.update("INSERT Usuario VALUES ('" + user.getCedula() + "', '" + user.getNombre() + "', '"
				+ user.getApellido() + "', '" + user.getCorreo() + "', '" + user.getPassword() + "');");
		/*
		 * Set<Telefono> telefonos = user.getTelefonos(); if (telefonos != null) { for
		 * (Telefono telefono : telefonos) {
		 * DAOFactory.getDAOFactory().getTelefonoDAO().create(telefono); } }
		 */
	}

	@Override
	public Usuario read(String cedula) {
		// TODO Auto-generated method stub
		Usuario usuario = null;
		ResultSet rsUsuario = conexionUno.query("SELECT * FROM agetelfdb.usuario WHERE usu_cedula='" + cedula + "';");
		try {
			if (rsUsuario != null && rsUsuario.next()) {
				usuario = new Usuario(rsUsuario.getString("usu_cedula"), rsUsuario.getString("usu_nombre"),
						rsUsuario.getString("usu_apellido"), rsUsuario.getString("usu_correo"),
						rsUsuario.getString("usu_contrasena"));
				Set<Telefono> telefonos = DAOFactory.getDAOFactory().getTelefonoDAO().findByUserId(usuario.getCedula());
				usuario.setTelefonos(telefonos);
				// System.out.println("Usuario buscado...."+usuario.getNombre());
			}
		} catch (SQLException e) {
			System.out.println("\">>>WARNING (JDBCUsuarioDAO:read): " + e.getMessage());
		}
		/*
		 * if (usuario==null) { return null; } /** Busca todos los telefonos mediante la
		 * cedula de un usuario
		 */
		/*
		 * Set<Telefono>
		 * telefonos=DAOFactory.getDAOFactory().getTelefonoDAO().findByUserId(usuario.
		 * getCedula()); if (telefonos != null) { Set<Telefono> telefonosAlt = new
		 * HashSet<Telefono>(); for (Telefono telefono : telefonos) {
		 * telefono.setUsuario(usuario); telefonosAlt.add(telefono); }
		 * usuario.setTelefonos(telefonosAlt); }
		 */
		return usuario;
	}

	/**
	 * Se modifican los telefono del usuario, no se modifica los datos del usuario.
	 **/
	@Override
	public void update(Usuario user) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println("Act:..." + user); TelefonoDAO
		 * telefonoDAO=DAOFactory.getDAOFactory().getTelefonoDAO(); Set<Telefono>
		 * telefonos = telefonoDAO.findByUserId(user.getCedula());
		 * conexionUno.query("");
		 */
		/*
		 * conexionUno.update("UPDATE Usuario SET usu_nombre = '" + user.getNombre() +
		 * "', usu_contrasena = '" + user.getPassword() + "', usu_apellido= " +
		 * user.getApellido() + "', usu_correo = '" + user.getCorreo() +
		 * " WHERE usu_cedula = " + user.getCedula());
		 */
	}

	@Override
	public void delete(Usuario user) {
		// TODO Auto-generated method stub
		conexionUno.update("DELETE FROM agetelfdb.usuario WHERE usu_cedula = " + user.getCedula());
	}

	@Override
	public List<Usuario> find() {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = new ArrayList<Usuario>();
		ResultSet rsUsuario = conexionUno.query("SELECT * FROM agetelfdb.usuario;");
		try {
			while (rsUsuario.next()) {
				Usuario user = new Usuario(rsUsuario.getString("usu_cedula"), rsUsuario.getString("usu_nombre"),
						rsUsuario.getString("usu_apellido"), rsUsuario.getString("usu_correo"),
						rsUsuario.getString("usu_contrasena"));

//				ResultSet rsTelefonos=conexionDos.query(
//						"SELECT * FROM telefono WHERE usu_cedula="+u.getCedula()+";");

				Set<Telefono> telefonos = DAOFactory.getDAOFactory().getTelefonoDAO().findByUserId(user.getCedula());
				user.setTelefonos(telefonos);
				/*
				 * if (telefonos != null) {
				 * 
				 * // while (rsTelefonos.next()) { // Telefono t=new
				 * Telefono(rsTelefonos.getString("tel_numero"),rsTelefonos.getString("tel_tipo"
				 * ),rsTelefonos.getString("tel_operadora")); // tels.add(t); // } Set<Telefono>
				 * telsAlt = new HashSet<Telefono>(); for (Telefono telefono : telefonos) {
				 * telefono.setUsuario(user); telsAlt.add(telefono); user.setTelefonos(telsAlt);
				 * } }
				 */
				usuarios.add(user);
			}
		} catch (SQLException e) {
			System.out.println(">>>>>Error en JDBCUsuarioDAO:find " + e.getMessage());
			return null;
		}

		return usuarios;
	}

	/**
	 * Busca al usuario por el correo
	 **/
	@Override
	public Usuario findByCorreo(String correo) {
		// TODO Auto-generated method stub
		Usuario usuario = null;
		ResultSet rsUsuario = conexionUno.query("SELECT * FROM usuario WHERE usu_correo='" + correo + "';");
		try {
			if (rsUsuario != null && rsUsuario.next()) {
				usuario = new Usuario(rsUsuario.getString("usu_cedula"), rsUsuario.getString("usu_nombre"),
						rsUsuario.getString("usu_apellido"), rsUsuario.getString("usu_correo"),
						rsUsuario.getString("usu_contrasenia"));
			}
		} catch (SQLException e) {
			System.out.println("\">>>WARNING (JDBCUsuarioDAO:read): " + e.getMessage());
		}
		if (usuario == null) {
			return null;
		}
		/**
		 * Busca todos los telefonos mediante el correo de un usuario
		 */
		Set<Telefono> telefonos = DAOFactory.getDAOFactory().getTelefonoDAO().findByUserId(usuario.getCedula());
		if (telefonos != null) {
			Set<Telefono> telefonosFinal = new HashSet<Telefono>();
			for (Telefono telefono : telefonos) {
				telefono.setUsuario(usuario);
				telefonosFinal.add(telefono);
			}
			usuario.setTelefonos(telefonosFinal);
		}
		return usuario;
	}

	@Override
	public Usuario findUser(String correo, String pass) {
		// TODO Auto-generated method stub
		System.out.println("Entro: Usuario: " + correo + " Pass: " + pass);
		Usuario user = null;
		ResultSet rs = conexionUno.query("SELECT * FROM agetelfdb.usuario WHERE usu_correo = '" + correo
				+ "' AND usu_contrasena = '" + pass + "';");
		System.out.println("Resultset" + rs);
		try {
			System.out.println("Entro al try findUser primero");
			if (rs != null && rs.next()) {
				System.out.println("Entro al try findUser");
				user = new Usuario(rs.getString("usu_cedula"), rs.getString("usu_nombre"), rs.getString("usu_apellido"),
						rs.getString("usu_correo"), rs.getString("usu_contrasena"));
				Set<Telefono> telefonos = DAOFactory.getDAOFactory().getTelefonoDAO().findByUserId(user.getCedula());
				user.setTelefonos(telefonos);
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDAO:findUser): " + e.getMessage());
		}
		return user;
	}

	@Override
	 public List<Usuario> findByIdOrMail(String context) {
        List<Usuario> users = new ArrayList<>();
        if (context.equals("all")) {
            ResultSet rs = conexionUno.query("SELECT * FROM usuario;");
            try {

                while (rs.next()) {
                    Usuario user = new Usuario(rs.getString("usu_cedula"), rs.getString("usu_nombre"), rs.getString("usu_apellido"), rs.getString("usu_correo"), rs.getString("usu_contrasena"));
                    Set<Telefono> telefonos = DAOFactory.getDAOFactory().getTelefonoDAO().findByUserId(user.getCedula());
                    user.setTelefonos(telefonos);
                    
                    users.add(user);
                }

            } catch (SQLException e) {
                System.out.println(">>>WARNING (JDBCUserDAO:findByIdOrMail): " + e.getMessage());
            }

        } else {
            ResultSet rs = conexionUno.query("SELECT * FROM usuario " + "WHERE usu_cedula = '" + context + "' OR usu_correo = '" + context + "';");
            try {
                if (rs != null && rs.next()) {
                    Usuario user = new Usuario(rs.getString("usu_cedula"), rs.getString("usu_nombre"), rs.getString("usu_apellido"), rs.getString("usu_correo"), rs.getString("usu_contrasena"));
                    Set<Telefono> telefonos = DAOFactory.getDAOFactory().getTelefonoDAO().findByUserId(user.getCedula());
                    user.setTelefonos(telefonos);
                    //System.out.println("Todos los usuarios por correo....." +context+" con nombre"+ user.getNombre());
                    users.add(user);
                }
            } catch (SQLException e) {
                System.out.println(">>>WARNING (JDBCUserDAO:findByIdOrMail): " + e.getMessage());
            }
        }

        return users;
    }

}
