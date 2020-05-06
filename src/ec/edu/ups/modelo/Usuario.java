/**
 * 
 */
package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.Set;


/**
 * @author Orlando Real
 *
 */
public class Usuario implements Serializable{

	/**
	 * Atributo necesario para el envío de objetos a través de una comunicación, en
	 * este caso hacia la base de datos
	 */
	private static final long serialVersionUID = 1L;
	private String cedula;
	private String nombre;
	private String apellido;
	private String correo;
	private String password;
	private Set<Telefono> telefonos;
	
	public Usuario() {
		// Constructor obligatorio
	}

	public Usuario(String cedula, String nombre, String apellido, String correo, String password) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.password = password;
	}


	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(Set<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	// Método toString que permite describir a un objeto
	@Override
	public String toString() {
		return "Usuario [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", password=" + password + ", telefonos=" + telefonos + "]";
	}
}
