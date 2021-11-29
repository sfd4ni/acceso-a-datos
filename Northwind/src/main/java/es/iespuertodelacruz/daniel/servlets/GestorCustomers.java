package es.iespuertodelacruz.daniel.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.repositories.CustomerRepository;
import es.iespuertodelacruz.daniel.repositories.OrderRepository;

/**
 * Servlet implementation class GestorCustomers
 */
@WebServlet({ "/GestorCustomers", "/gestorcustomers" })
public class GestorCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestorCustomers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
		CustomerRepository customerR = new CustomerRepository(emf);
		EntityManager em = emf.createEntityManager();
		
		String redirect = "users/customers.jsp";
		if (idStr != null) {
			request.getSession().setAttribute("customer", customerR.findById(idStr));
			redirect = "users/customer.jsp";
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
