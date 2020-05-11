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
@WebFilter(urlPatterns = {"/Private/*"})
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

		HttpServletRequest reqHttp = (HttpServletRequest)request;
		HttpSession session = reqHttp.getSession();
		System.out.println("Entra filtroSesion: " + session.getAttribute("sesionID"));
		System.out.println("Entra filtroSesion 2: " + session.getId());
		if (session.getAttribute("iniciado") !=null) {
			System.out.println("entra a la validacion de FILTRO");
			chain.doFilter(request, response);
		}else{
			System.out.println("El fitro marica dice que la sesion no esta iniciada");
			//((HttpServletResponse)response).sendRedirect("/publica/login.jsp");
			request.getRequestDispatcher("/Public/HTML/inicioSesion.html").forward(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
