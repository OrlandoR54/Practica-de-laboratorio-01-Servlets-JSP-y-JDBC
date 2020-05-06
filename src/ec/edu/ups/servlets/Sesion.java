package ec.edu.ups.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;

/**
 * Servlet implementation class Sesion
 */
@WebServlet("/Sesion")
public class Sesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = "";
		String password = "";

		int num = 0;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html><head><meta charset='UTF-8'><title>Sesion</title></head><body>");
		email = request.getParameter("usr").toLowerCase();
		password = request.getParameter("pass");
		HttpSession session = request.getSession();
		
		
		UsuarioDAO usrDAO = DAOFactory.getFactory().getUserDAO();
		
		//usrDAO.read(id)
		
		if (email.equals("orlandojrv@hotmail.com") && password.equals("123")) {
			out.print("<h1>Prueba</h1>");
			if (session.isNew()) {
				out.print("<h2>Gracias por acceder al servidor</h2>");
				session.setAttribute("accesos", 1);
				//response.sendRedirect("Sesion.html");
			} else {
				out.println("<h2>Gracias por volver " + email + "</h2>");
				out.println("<p>Identificador de sesion: " + session.getId() + "</p>");
				out.println("<p>Fecha de creacion: " + new Date(session.getCreationTime()) + "</p>");
				out.println("<p>Fecha de ultimo acceso: " + new Date(session.getLastAccessedTime()) + "</p>");
				num = (Integer) session.getAttribute("accesos");
				out.println("<p>Accesos realizados: " + num + "</p>");
				session.setAttribute("accesos", num + 1);		
				session.setAttribute("usuario", email);
				session.setAttribute("contrasena", password);
				//response.sendRedirect("Sesion.html");
			}
		}else {
			out.print("<h1>Usuario denegado</h2>");
			out.println("<a href='index.html'>Iniciar Sesion</a></body></html>");
		}
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
