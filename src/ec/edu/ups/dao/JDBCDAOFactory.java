/**
 * 
 */
package ec.edu.ups.dao;

import ec.edu.ups.mysql.jdbc.JDBCTelefonoDAO;
import ec.edu.ups.mysql.jdbc.JDBCUsuarioDAO;

/**
 * @author Orlando Real
 *
 */
public class JDBCDAOFactory extends DAOFactory {

	@Override
	public void createTables() {
		// TODO Auto-generated method stub

		//this.getCategoryDAO().createTable();
		this.getUserDAO().createTable();
		this.getTelefonoDAO().createTable();
	}

	@Override
	public UsuarioDAO getUserDAO() {
		// TODO Auto-generated method stub
		return new JDBCUsuarioDAO();
	}

	@Override
	public TelefonoDAO getTelefonoDAO() {
		// TODO Auto-generated method stub
		return new JDBCTelefonoDAO();
	}

}
