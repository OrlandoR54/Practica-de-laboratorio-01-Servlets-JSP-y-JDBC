/**
 * 
 */
package ec.edu.ups.dao;

import ec.edu.ups.modelo.Usuario;

/**
 * 
 * Interface UserDAO.
 * 
 * 
 * En esta interface se pueden agregar método específicos para el manejo
 * del objeto User, por ejemplo: buscarUsuarioPorName,
 * buscarUsuarioPorCedula, entre otras.
 * 
 * @author Orlando Real
 *
 */
public interface UsuarioDAO extends GenericDAO<Usuario, String>{

}
