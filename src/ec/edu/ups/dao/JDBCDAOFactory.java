/**
 * 
 */
package ec.edu.ups.dao;

import ec.edu.ups.mysql.jdbc.JDBCTelefonoDAO;
import ec.edu.ups.mysql.jdbc.JDBCUsuarioDAO;

/**
 * 
 * Esta clase implementa los m�todos abstractos de la clase DAOFatcory
 * gestionando de esta manera a trav�s de esta clase el acceso a los DAOs que
 * persistiran la informaci�n en la base de datos.
 * 
 * @author Orlando Real
 *
 */
public class JDBCDAOFactory extends DAOFactory {

	@Override
	public void createTables() {
		// TODO Auto-generated method stub
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
