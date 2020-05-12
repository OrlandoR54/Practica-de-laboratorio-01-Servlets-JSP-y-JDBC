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
		
		//Usuario user = DAOFactory.getDAOFactory().getUserDAO().read(String.valueOf(request.getSession().getAttribute("userID")));
        
		System.out.println("Tamano lista User: " + users.size());
		
		System.out.println("RecuperaBusqueda: " + context);
		System.err.println(users);
		
		//String url = "/Sesion?usr=" + user.getCorreo() + "&pass=" + user.getPassword();
		//System.out.println("URL" + url);
		getServletContext().getRequestDispatcher("/Private/Busqueda.jsp").forward(request, response);
		//getServletContext().getRequestDispatcher(url).forward(request, response);
		//request.getRequestDispatcher("/Private/Busqueda.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
