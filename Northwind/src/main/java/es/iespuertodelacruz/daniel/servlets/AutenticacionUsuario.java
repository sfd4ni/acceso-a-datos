package es.iespuertodelacruz.daniel.servlets;

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

import es.iespuertodelacruz.daniel.entities.Usuario;

/**
 * Servlet Filter implementation class AutenticacionUsuario
 */
@WebFilter({ "/AutenticacionUsuario", "/users/*" })
public class AutenticacionUsuario implements Filter {

    /**
     * Default constructor. 
     */
    public AutenticacionUsuario() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest requestSess = (HttpServletRequest) request;
		
		Usuario user = (Usuario) requestSess.getSession().getAttribute("usuario");
		
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse responseSess = (HttpServletResponse) response;
			responseSess.sendRedirect("../login.jsp");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
