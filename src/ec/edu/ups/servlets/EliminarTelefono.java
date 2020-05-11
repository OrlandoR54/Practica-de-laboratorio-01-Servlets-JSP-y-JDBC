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
 * Servlet implementation class EliminarTelefono
 */
@WebServlet("/EliminarTelefono")
public class EliminarTelefono extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarTelefono() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String idTelefono = request.getParameter("tel_id");
		
		TelefonoDAO telefonoDAO = DAOFactory.getDAOFactory().getTelefonoDAO();
		Telefono telefono = telefonoDAO.findbyTelefonoId(Integer.parseInt(idTelefono));
		Usuario user = DAOFactory.getDAOFactory().getUserDAO().read(String.valueOf(request.getSession().getAttribute("userID")));
		
		telefono.setId(Integer.parseInt(idTelefono));
		telefono.setUsuario(user);
		
		telefonoDAO.delete(telefono);
		System.err.println("telefono a eliminar.. " + telefono);
		String url = "/Sesion?usr=" + user.getCorreo() + "&pass=" + user.getPassword();
		System.out.println("URL" + url);
		getServletContext().getRequestDispatcher(url).forward(request, response);
			
	}

}
