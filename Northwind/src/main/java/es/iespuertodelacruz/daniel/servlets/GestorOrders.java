package es.iespuertodelacruz.daniel.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.repositories.OrderRepository;
import es.iespuertodelacruz.daniel.repositories.ProductRepository;

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
		String customerId = request.getParameter("customerid");
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
		OrderRepository orderR = new OrderRepository(emf);
		ProductRepository productR = new ProductRepository(emf);
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
		if (customerId != null) {
			request.getSession().setAttribute("productsList", productR.findAll());
			request.getSession().setAttribute("customerId", customerId);
			redirect = "users/neworder.jsp";
		}
		request.getRequestDispatcher(redirect).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product = request.getParameter("productStr");
		ArrayList<String> carritoList = (ArrayList<String>) request.getSession().getAttribute("carritoList");
		String redirect = "users/neworder.jsp";
		if (product != null) {
			if (carritoList == null) {
				carritoList = new ArrayList<>();
			}
			carritoList.add(product);
			request.getSession().setAttribute("carritoList", carritoList);
		}
		request.getRequestDispatcher(redirect).forward(request, response);
	}

}
