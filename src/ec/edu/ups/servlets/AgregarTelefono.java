package ec.edu.ups.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;

/**
 * Servlet implementation class AgregarTelefono
 */
@WebServlet("/AgregarTelefono")
public class AgregarTelefono extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarTelefono() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		String operadora = request.getParameter("operadora");
		String num_ced = request.getParameter("num_ced");
		TelefonoDAO telefonoDAO = DAOFactory.getDAOFactory().getTelefonoDAO();
		Telefono telefono = new Telefono(numero, tipo, operadora);
		Usuario user = DAOFactory.getDAOFactory().getUserDAO().read(String.valueOf(request.getSession().getAttribute("userID")));
		System.out.println("Agrega Tel Usuario " + user);
		telefono.setUsuario(user);
		telefonoDAO.create(telefono);
		
		System.out.println("Cedula usuario, agrega telf: " + String.valueOf(request.getSession().getAttribute("userID")));
		String url = "/Sesion?usr=" + user.getCorreo() + "&pass=" + user.getPassword();
		System.out.println("URL" + url);
		getServletContext().getRequestDispatcher(url).forward(request, response);
		//response.sendRedirect("/Practica-de-laboratorio-01-Servlets-JSP-y-JDBC/Private/SesionUser.jsp");
		//request.getRequestDispatcher("/Private/SesionUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
