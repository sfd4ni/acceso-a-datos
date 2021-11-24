package es.iespuertodelacruz.jc.monedaswebjpa.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.jc.monedaswebjpa.repositories.MonedaRepository;

/**
 * Servlet implementation class GestorMonedas
 */
@WebServlet({ "/GestorMonedas", "/gestormonedas" })
public class GestorMonedas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestorMonedas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
		MonedaRepository monedaR = new MonedaRepository(emf);
		String redirect = "users/monedas.jsp";
		if (idStr != null) {
			Integer idMoneda = null;
			try {
				idMoneda = Integer.parseInt(idStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (idMoneda != null) {
				request.getSession().setAttribute("moneda", monedaR.findById(idMoneda));
				redirect = "users/moneda.jsp";
			}
		}
		request.getRequestDispatcher(redirect).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
