package ec.edu.ups.mysql.jdbc;

import ec.edu.ups.dao.GenericDAO;


/**
 * 
 * La clase abstracta JDBCGenericDAO implementa los m�todos de la clase
 * GenericDAO. Teniendo de esta manera una clase generica abstracta que ser�
 * implementada por cada clase espec�fica y permitir� persistir la informaci�n
 * en la base de datos.
 * 
 * As� como tambi�n, en esta clase abstracta se obtiene dos conexiones a la base
 * de datos para poder persistir la informaci�n.
 * 
 * @author Orlando Real
 *
 * @param <T>  la entidad o modelo que ser� persistida en la base de datos
 * @param <ID> el id o llave primaria por la cu�l se identificara al modelo como
 *             valor �nico
 */
public abstract class JDBCGenericDAO<T, ID> implements GenericDAO<T, ID> {

	protected ContextJDBC conexionUno = ContextJDBC.getJDBC1();
	protected ContextJDBC conexionDos = ContextJDBC.getJDBC2();
}
