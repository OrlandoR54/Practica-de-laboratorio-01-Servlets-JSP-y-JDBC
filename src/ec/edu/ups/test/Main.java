/**
 * 
 */
package ec.edu.ups.test;

import java.util.HashSet;
import java.util.Set;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;

/**
 * @author Orlando Real
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		 
		UsuarioDAO usuarioDAO = DAOFactory.getDAOFactory().getUserDAO();
		
		/*Telefono t1,t2,t3,t4,t5,t6;
		
		t1 = new Telefono(0,"0102309182","movil","cnt");
		t2 = new Telefono(0,"0102309183","movil","cnt");
		t3 = new Telefono(0,"0102309184","fijo","movistar");
		
		t4 = new Telefono(0,"0102309185","movil","claro");
		t5 = new Telefono(0,"0102309186","fijo","tuenti");
		t6 = new Telefono(0,"0102309187","movil","movistar");
		
		
		Usuario u1 = new Usuario("0106655517","juan","gustavo","juan@hotmail.com","1234");
		Usuario u2 = new Usuario("0106655516","malla","ramirez","malla@hotmail.com","1234");
		
		t1.setUsuario(u1);
		t2.setUsuario(u1);
		t3.setUsuario(u1);
		
		t4.setUsuario(u2);
		t5.setUsuario(u2);
		t6.setUsuario(u2);
		
		Set<Telefono> lisTels1 =new HashSet<Telefono>();
		Set<Telefono> lisTels2 =new HashSet<Telefono>();
		
		lisTels1.add(t1);
		lisTels1.add(t2);
		lisTels1.add(t3);
		
		lisTels2.add(t4);
		lisTels2.add(t5);
		lisTels2.add(t6);
		
		
		u1.setTelefonos(lisTels1);
		u2.setTelefonos(lisTels2);
		
		
		usuarioDAO.create(u1);
		usuarioDAO.create(u2);*/
		Usuario usuario = usuarioDAO.findUser("oreal@est.ups.edu.ec", "Bruno.");
		System.out.println("Muestra: " + usuario);
		
		/*System.out.println("buscar un el telefono en base al numero de cedula del usuario");
		
		TelefonoDAO telefonoDAO=DAOFactory.getDAOFactory().getTelefonoDAO();				
		Set<Telefono> tels=telefonoDAO.findByUserId("010665516");	
		for (Telefono telefono : tels) {
			System.out.println("numero: "+telefono.getNumero());
			System.out.println("operadora: "+telefono.getOperadora());
			System.out.println("id: "+telefono.getId());
		}
		System.out.println("---------------------------------------------------------------");

		
		for (Usuario usuario : usuarioDAO.find()) {
			System.out.println("usuario: "+usuario.getNombre());
			Set<Telefono> tls=usuario.getTelefonos();
			for (Telefono telefono : tls) {
				System.out.println("-----------operadora: "+telefono.getOperadora()+" num: "+telefono.getNumero()+" id: "+telefono.getId());
			}
			
		}
		
		System.out.println("---------------------------------------------------------------");
		
						
			
			
		for (Telefono telefono : telefonoDAO.find()) {
			System.out.println("numero: " + telefono.getNumero()+" operadora: "+ telefono.getOperadora() +" id: "+telefono.getId());					
			System.out.println("--------------------nombre de usuario: "+ telefono.getUsuario().getNombre());
		}
		
		
		System.out.println("---------------------------------------------------------------");
		//Telefono tt = telefonoDAO.findbyTelefonoId(5);
		
		/*
		UsuarioDAO usuarioDAO = DAOFactory.getDAOFactory().getUserDAO();
		//Usuario usuario= usuarioDAO.findByCorreo("natu@illaisaca")
		Usuario usuario = usuarioDAO.read("0106655517");
		Set<Telefono> tels=usuario.getTelefonos();
		System.out.println(usuario.getNombre());
		for (Telefono telefono : tels) {
			System.out.println("----------tel: "+ telefono.getNumero());
		}*/
	}

}
