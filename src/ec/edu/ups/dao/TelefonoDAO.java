/**
 * 
 */
package ec.edu.ups.dao;

import java.util.Set;

import ec.edu.ups.modelo.Telefono;

/**
 * @author Orlando Real
 *
 */
public interface TelefonoDAO extends GenericDAO<Telefono, String>{

	public abstract Set<Telefono> findByUserId(String cedulaUser);
	
	public abstract Set<Telefono> findByEmailId(String correo);
	
}
