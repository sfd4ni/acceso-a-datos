package es.iespuertodelacruz.daniel.servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import es.iespuertodelacruz.daniel.entities.Usuario;
import es.iespuertodelacruz.daniel.repositories.OrderRepository;
import es.iespuertodelacruz.daniel.repositories.UsuarioRepository;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		String redirect = "login.jsp";
		if(usuario != null)
			redirect="users/orders.jsp";
		else {
			String paramUsuario = request.getParameter("user");
			String paramPassword = request.getParameter("password");
			EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
			UsuarioRepository UsuarioR = new UsuarioRepository(emf);
			
			usuario = UsuarioR.findByName(paramUsuario);
			
			if(usuario != null) {
				System.out.println("Hola");
				boolean okLogin =  true;
				//boolean okLogin = BCrypt.checkpw(paramPassword,usuario.getPassword());
				/*
				 * Para una pagina de registro. Se usaría la siguiente sentencia:
				 * 	String enHash = BCrypt.hashpw( paramPassword, BCrypt.gensalt(10));
				 * Observar que el 10 es el salt por defecto si se quiere se puede
				 * establecer otro. Lo importante es que todas las password-hash
				 * sean establecidas iguales 
				*/
				if( okLogin) {
					System.out.println("Bien hecho.");
					request.getSession().setAttribute("usuario", usuario);
					OrderRepository orderR = new OrderRepository(emf);
					request.getSession().setAttribute("listaOrders", orderR.findAll());
					redirect="users/orders.jsp";
				}
				
			}
		}
		response.sendRedirect(redirect);
	}

}
