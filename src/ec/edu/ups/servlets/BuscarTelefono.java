package ec.edu.ups.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class BuscarTelefono
 */
@WebServlet("/BuscarTelefono")
public class BuscarTelefono extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarTelefono() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String numTelf = request.getParameter("numTelf");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		TelefonoDAO telefonoDAO = DAOFactory.getDAOFactory().getTelefonoDAO();
	
		Usuario user = DAOFactory.getDAOFactory().getUserDAO().read(String.valueOf(request.getSession().getAttribute("userID")));

		Telefono telefono = telefonoDAO.read(numTelf);	
		
		String url = null;
		if (telefono != null) {
			request.setAttribute("telefono", telefono); // Estara disponible como ${telefono} en JSP
			System.out.println("numero del telefono: "+ telefono.getNumero()+" tipo: "+ telefono.getTipo() + "Operadore" + telefono.getOperadora());
			url = "/Sesion?usr=" + user.getCorreo() + "&pass=" + user.getPassword();
		}else {
			String urlLoc = "/Sesion?usr=" + user.getCorreo() + "&pass=" + user.getPassword();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No se pudo encontrar el número');");
			out.println("location='/Practica-de-laboratorio-01-Servlets-JSP-y-JDBC" + urlLoc + "';");
			out.println("</script>");
		}		
		System.out.println("URL" + url);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
