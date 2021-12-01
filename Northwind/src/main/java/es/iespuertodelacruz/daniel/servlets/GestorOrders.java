package es.iespuertodelacruz.daniel.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.entities.Customer;
import es.iespuertodelacruz.daniel.entities.Order;
import es.iespuertodelacruz.daniel.entities.OrderDetail;
import es.iespuertodelacruz.daniel.entities.Product;
import es.iespuertodelacruz.daniel.repositories.CustomerRepository;
import es.iespuertodelacruz.daniel.repositories.OrderRepository;
import es.iespuertodelacruz.daniel.repositories.ProductRepository;
import es.iespuertodelacruz.daniel.repositories.UsuarioRepository;

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
		CustomerRepository customerR = new CustomerRepository(emf);
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
			request.getSession().setAttribute("customer", customerR.findById(customerId));
			redirect = "users/neworder.jsp";
		}
		request.getRequestDispatcher(redirect).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product = request.getParameter("productStr");
		String button = request.getParameter("add");
		if (button == null) {
			button = request.getParameter("save");
		}
		String productId = request.getParameter("productId");
		String productQuantity = request.getParameter("productQuantity");
		ArrayList<OrderDetail> carritoList = (ArrayList<OrderDetail>) request.getSession().getAttribute("carritoList");
		String redirect = "users/neworder.jsp";
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
		OrderRepository orderR = new OrderRepository(emf);
		ProductRepository productR = new ProductRepository(emf);
		if (button.equals("add")) {
			if (product != null) {
				if (carritoList == null) {
					carritoList = new ArrayList<>();
				}
				if (productQuantity != null) {
					Product producto = productR.findById(Integer.parseInt(productId));
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setQuantity(Integer.parseInt(productQuantity));
					orderDetail.setUnitPrice(producto.getUnitPrice());
					orderDetail.setDiscount(0);
					orderDetail.setProduct(producto);
					carritoList.add(orderDetail);
					request.getSession().setAttribute("carritoList", carritoList);
				}
				
			}
		} else if (button.equals("save")) {
			Order order = new Order();
			Customer customer = (Customer) request.getSession().getAttribute("customer");
			order.setCustomer(customer);
			order.setOrderDetails(carritoList);
			orderR.save(order);
		}
		
		request.getRequestDispatcher(redirect).forward(request, response);
	}

}
