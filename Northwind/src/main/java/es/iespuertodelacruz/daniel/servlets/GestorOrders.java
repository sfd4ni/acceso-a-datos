package es.iespuertodelacruz.daniel.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.repositories.OrderRepository;

/**
 * Servlet implementation class GestorOrders
 */
@WebServlet({ "/GestorOrders", "/gestororders" })
public class GestorOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestorOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
		OrderRepository orderR = new OrderRepository(emf);
		EntityManager em = emf.createEntityManager();
		
		String redirect = "users/orders.jsp";
		if (idStr != null) {
			Integer idOrder = null;
			try {
				idOrder = Integer.parseInt(idStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (idOrder != null) {
				request.getSession().setAttribute("order", orderR.findById(idOrder));
				redirect = "users/order.jsp";
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
