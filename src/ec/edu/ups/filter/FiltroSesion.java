package ec.edu.ups.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroSesion
 */
@WebFilter("/FiltroSesion")
public class FiltroSesion implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltroSesion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpSession session = ((HttpServletRequest) request).getSession();
		// session.invalidate();
		/*
		 * session.removeAttribute("usuario"); session.removeAttribute("contrasena");
		 */
	
		/*if (session.getAttribute("iniciado") != null) {
			System.out.println("EL FILTRO CONSEDE EL ACCESO");
			chain.doFilter(request, response);
			request.getRequestDispatcher("/Private/SesionUser.jsp").forward(request, response);
		} else {
			System.out.println("No inicio sesion");
			// ((HttpServletResponse)response).sendRedirect("/publica/login.jsp");
		}*/

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession sesion = req.getSession();

		System.out.println("Entra filtroSesion: " + sesion.getAttribute("sesionID"));
		System.out.println("Entra filtroSesion 2: " + sesion.getId());
		if (String.valueOf(sesion.getAttribute("sesionID")).equals(String.valueOf(sesion.getId()))) {
			System.out.println("entra a la validacion");
			
			res.sendRedirect("Private/SesionUser.jsp");
			chain.doFilter(request, response);
		} else {
			// System.out.println("redirect login .....");
			res.sendRedirect("Public/HTML/inicioSesion.html");
			
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
