/**
 * 
 */
package ec.edu.ups.dao;

import java.util.List;

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
	public abstract Usuario findUser(String correo, String pass);
    public abstract List<Usuario> findByIdOrMail(String context);
    public Usuario findByCorreo(String correo);
}
