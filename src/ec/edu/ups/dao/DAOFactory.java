package ec.edu.ups.dao;


/**
 * @author Orlando Real
 *
 */
public abstract class DAOFactory {

	protected static DAOFactory factory = new JDBCDAOFactory();

	public static DAOFactory getDAOFactory() {
		return factory;
	}

	public abstract void createTables();

	public abstract UsuarioDAO getUserDAO();

	public abstract TelefonoDAO getTelefonoDAO();

}
