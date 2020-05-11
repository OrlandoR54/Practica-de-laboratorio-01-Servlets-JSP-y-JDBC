package ec.edu.ups.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
	UsuarioDAO userDAO;
	Usuario usuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sesion() {
        super();
        // TODO Auto-generated constructor stub
        userDAO = DAOFactory.getDAOFactory().getUserDAO();
        usuario = new Usuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String email = "";
		String password = "";
		
		email = request.getParameter("usr");
		password = request.getParameter("pass");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(email instanceof String){
	        System.out.println("Email: Es de tipo String");
	    }else{
	        System.out.println("No es de tipo String");
	    }
		
		if(password instanceof String){
	        System.out.println("Password: Es de tipo String");
	    }else{
	        System.out.println("No es de tipo String");
	    }
		
		if (email == "" && password == "") {
			System.out.println("Input vacios");
			response.sendRedirect("Public/HTML/inicioSesion.html");
		} else {
			usuario = userDAO.findUser(email, password);
			System.out.println("Sesion " + usuario);
			
			if (usuario != null) {
	            System.out.println("usuario encontrado");
	            HttpSession session = request.getSession(true);  //El usuario a iniciado sesion
	            //request.getSession().setAttribute("iniciado", session);
	            System.out.println("Sesion iniciada con id " + request.getSession().getId());
	            session.setAttribute("sesionID", String.valueOf(session.getId()));
	            session.setAttribute("userID", usuario.getCedula());
	            Date firstSession = new Date(session.getCreationTime());
	            Date lastSession = new Date(session.getLastAccessedTime());
	            System.out.println("Fecha de creacion: " + firstSession);
				System.out.println("Fecha de ultimo acceso: " + lastSession);
				session.setAttribute("usuario", usuario);
				request.getSession().setAttribute("iniciado", session);	
				request.setAttribute("usuario", usuario);
	            //request.getRequestDispatcher("/Private/SesionUser.jsp").forward(request, response);
	            getServletContext().getRequestDispatcher("/Private/SesionUser.jsp").forward(request, response);
	            
	        }else{
	        	System.out.println("No encontrado");
	        	out.println("<script type='text/javascript'>");
	        	out.println("alert('Usuario o contraseña incorrecta');");
	        	out.println("location='/Practica-de-laboratorio-01-Servlets-JSP-y-JDBC/Public/HTML/inicioSesion.html';");
	        	out.println("</script>");
	            //response.sendRedirect("/Practica-de-laboratorio-01-Servlets-JSP-y-JDBC/index.html");
	        }     
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
