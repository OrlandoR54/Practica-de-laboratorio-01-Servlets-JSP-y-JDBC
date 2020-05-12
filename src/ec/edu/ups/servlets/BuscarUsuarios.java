package ec.edu.ups.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;

/**
 * Servlet implementation class BuscarUsuarios
 */
@WebServlet("/BuscarUsuarios")
public class BuscarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String context = request.getParameter("busquedaUser");
		UsuarioDAO usuarioDAO = DAOFactory.getDAOFactory().getUserDAO();
		
		List<Usuario> users = usuarioDAO.findByIdOrMail(context);
		request.setAttribute("users", users);
		
		Usuario usuario = null;	
		System.err.println(busquedaUser);
		if (busquedaUser.contains("@")) {
			System.err.println("es correo");
			System.out.println(busquedaUser);
			usuario = usuarioDAO.findByCorreo(busquedaUser);
			
			telefonos = usuario.getTelefonos();
			for (Telefono telefono : telefonos) {
				System.out.println("tel: " + telefono.getNumero());
			}
		} else if (busquedaUser.matches("[0-9]+") && busquedaUser.length() == 10) {
			System.out.println("cedula valida");
			System.out.println(busquedaUser);
			usuario = usuarioDAO.read(busquedaUser);
			
			telefonos = usuario.getTelefonos();
			for (Telefono telefono : telefonos) {
				System.out.println("tel: " + telefono.getNumero());
			}
		} else {
			System.out.println("ERROR correo no valido o numero de cedula contiene caracteres");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
