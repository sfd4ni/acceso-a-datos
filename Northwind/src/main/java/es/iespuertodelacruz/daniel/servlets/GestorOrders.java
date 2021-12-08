package es.iespuertodelacruz.daniel.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
import es.iespuertodelacruz.daniel.entities.Usuario;
import es.iespuertodelacruz.daniel.repositories.CustomerRepository;
import es.iespuertodelacruz.daniel.repositories.EmployeeRepository;
import es.iespuertodelacruz.daniel.repositories.OrderRepository;
import es.iespuertodelacruz.daniel.repositories.ProductRepository;
import es.iespuertodelacruz.daniel.repositories.ShipperRepository;
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
		String back = request.getParameter("back");
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
		if (button == null) {
			button = request.getParameter("back");
		}
		String productId = request.getParameter("productId");
		String productQuantity = request.getParameter("productQuantity");
		ArrayList<OrderDetail> carritoList = (ArrayList<OrderDetail>) request.getSession().getAttribute("carritoList");
		ArrayList<Product> carritoProduct = (ArrayList<Product>) request.getSession().getAttribute("carritoProduct");
		String redirect = "users/neworder.jsp";
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
		OrderRepository orderR = new OrderRepository(emf);
		CustomerRepository customerR = new CustomerRepository(emf);
		ProductRepository productR = new ProductRepository(emf);
		EmployeeRepository employeeR = new EmployeeRepository(emf);
		ShipperRepository shipperR = new ShipperRepository(emf);
		EntityManager em = emf.createEntityManager();
		if (button.equals("Add")) {
			if (product != null) {
				if (carritoList == null) {
					carritoList = new ArrayList<>();
					carritoProduct = new ArrayList<>();
				}
				
				if (productQuantity != null) {
					Product producto = productR.findById(Integer.parseInt(productId));
					Integer quantity = null;
					try {
						quantity = Integer.parseInt(productQuantity);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (quantity != null) {
						OrderDetail orderDetail = null;
						boolean exist = false;
						for (int i = 0; i < carritoList.size(); i++) {
							OrderDetail orderDetailCarrito = carritoList.get(i);
							if (orderDetailCarrito.getProduct().getProductID() == producto.getProductID()) {
								exist = true;
								if (orderDetailCarrito.getQuantity() + quantity <= producto.getUnitsInStock()) {
									orderDetailCarrito.setQuantity(orderDetailCarrito.getQuantity() + quantity);
									for (Product productCar : carritoProduct) {
										if (productCar.getProductID() == producto.getProductID()) {
											productCar.setUnitsInStock(productCar.getUnitsInStock() - orderDetailCarrito.getQuantity());
										}
									}
								}
								if (orderDetailCarrito.getQuantity() == 0)
									carritoList.remove(i);
								break;
							}
						}
						if (!exist) {
							if (quantity <= producto.getUnitsInStock()) {
								orderDetail = new OrderDetail();
								orderDetail.setQuantity(Integer.parseInt(productQuantity));
								orderDetail.setUnitPrice(producto.getUnitPrice());
								producto.setUnitPrice(producto.getUnitPrice() - quantity);
								orderDetail.setDiscount(0);
								orderDetail.setProduct(producto);
								carritoList.add(orderDetail);
								producto.setUnitsInStock(producto.getUnitsInStock() - quantity);
								carritoProduct.add(producto);
								request.getSession().setAttribute("carritoList", carritoList);
								request.getSession().setAttribute("carritoProduct", carritoProduct);
							}
						}
						
					}
				}
				
			}
		} else if (button.equals("Save")) {
			if (carritoList != null) {
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
				Order order = new Order();
				Customer customer = (Customer) request.getSession().getAttribute("customer");
				order.setOrderDetails(carritoList);
				order.setCustomer(customer);
				order.setEmployee(employeeR.findById(usuario.getIdusuario()));
				order.setFreight(30.00);
				order.setOrderDate(new Date());
				order.setRequiredDate(new Date());
				order.setShipAddress(customer.getAddress());
				order.setShipCity(customer.getCity());
				order.setShipCountry(customer.getCountry());
				order.setShipName("Que Delícia");
				order.setShippedDate(new Date());
				order.setShipper(shipperR.findById(1));
				order.setShipPostalCode(customer.getPostalCode());
				order.setShipRegion("");
				orderR.save(order);
				request.getSession().setAttribute("carritoList", null);
				for(Product productCar : carritoProduct) {
					productR.save(productCar);
				}
				request.getSession().setAttribute("carritoProduct", null);
			}
			
		} else if (button.equals("Back")) {
			Customer customer = (Customer) request.getSession().getAttribute("customer");
			request.getSession().setAttribute("customer", customerR.findById(customer.getCustomerID()));
			redirect = "users/customer.jsp";
			request.getSession().setAttribute("carritoList", null);
		}
		
		request.getRequestDispatcher(redirect).forward(request, response);
	}

}
