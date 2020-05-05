package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		conexionUno.update("INSERT Usuario VALUES (" + user.getId() + ", " + user.getLevel() + ", '" + user.getName()
				+ "', '" + user.getPassword() + "')");
	}

	@Override
	public Usuario read(String id) {
		// TODO Auto-generated method stub
		Usuario user = null;
		ResultSet rs = conexionUno.query("SELECT * FROM Usuario WHERE id=" + id);
		try {
			if (rs != null && rs.next()) {
				user = new User(rs.getInt("id"), rs.getInt("level"), rs.getString("name"), rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDAO:read): " + e.getMessage());
		}
		if (user == null) {
			return null;
		} else {
			UserDetail detail = DAOFactory.getFactory().getUserDetailDAO().findByUserId(user.getId());
			if (detail != null) {
				user.setDetail(detail);
				// detail.setUser(user);
			}
		}
		return user;
	}

	@Override
	public void update(Usuario user) {
		// TODO Auto-generated method stub

		UserDetailDAO userDetailDAO = DAOFactory.getFactory().getUserDetailDAO();
		UserDetail detail = userDetailDAO.findByUserId(user.getId());
		System.out.println("Act:..." + user);
		conexionUno.update("UPDATE Usuario SET name = '" + user.getName() + "', password = '" + user.getPassword()
				+ "', level= " + user.getLevel() + " WHERE id = " + user.getId());

		if (user.getDetail() == null && detail != null) {
			userDetailDAO.delete(detail);
		} else if (user.getDetail() != null && detail == null) {
			userDetailDAO.create(user.getDetail());
		} else if (user.getDetail() != null && detail != null) {
			userDetailDAO.update(user.getDetail());
		}
	}

	@Override
	public void delete(Usuario user) {
		// TODO Auto-generated method stub
		if (user.getDetail() != null) {
			DAOFactory.getFactory().getUserDetailDAO().delete(user.getDetail());
		}
		conexionUno.update("DELETE FROM Usuario WHERE id = " + user.getId());
	}

	@Override
	public List<Usuario> find() {
		// TODO Auto-generated method stub
		List<Usuario> list = new ArrayList<Usuario>();
		ResultSet rs = conexionUno.query("SELECT * FROM Usuario");
		try {
			while (rs.next()) {
				Usuario user = new Usuario(rs.getInt("id"), rs.getInt("level"), rs.getString("name"),
						rs.getString("password"));
				list.add(user);

			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUserDAO:find): " + e.getMessage());
		}
		for (int i = 0; i < list.size(); i++) {
			Usuario user = list.get(i);
			UserDetail detail = DAOFactory.getFactory().getUserDetailDAO().findByUserId(user.getId());
			if (detail != null) {
				// detail.setUser(user);
				user.setDetail(detail);
				list.set(i, user);
			}
		}
		return list;
	}

}
