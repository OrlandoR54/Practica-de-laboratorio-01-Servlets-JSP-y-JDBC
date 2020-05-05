package ec.edu.ups.dao;


/**
 * @author Orlando Real
 *
 */
public abstract class DAOFactory {

	protected static DAOFactory factory = new JDBCDAOFactory();

	public static DAOFactory getFactory() {
		return factory;
	}

	public abstract void createTables();

	//public abstract CategoryDAO getCategoryDAO();

	public abstract UsuarioDAO getUserDAO();

	public abstract TelefonoDAO getTelefonoDAO();


}
