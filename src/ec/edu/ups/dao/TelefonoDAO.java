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

	/*
	 * Metodo propio de la interface telefono DAO para obtener un telefono mediante el numero de cedula
	 * 
	 * */
	public abstract Set<Telefono> findByUserId(String cedula);
	
	public abstract Telefono findbyTelefonoId(int tel_codigo);
	
}
