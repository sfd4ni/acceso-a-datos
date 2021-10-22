package es.iespuertodelacruz.dbr.matriculas.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.dbr.matriculas.modelo.Mensaje;



public class Principal extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    public Principal() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse 
    		response) throws ServletException, IOException {
		/*String hola = request.getParameter("hola");
		String adios = request.getParameter("adios");
		String redireccion = "index.html";
		if (hola != null && !hola.isEmpty()) {
			redireccion = "hola.jsp";
		}
		if (adios != null && !adios.isEmpty()) {
			redireccion = "adios.jsp";
		}
		request.getRequestDispatcher(redireccion).forward(request, response);*/
    	request.setCharacterEncoding("UTF-8");
		ArrayList<Mensaje> listaMensajes = (ArrayList<Mensaje>) 
				request.getServletContext().getAttribute("listaMensajes");
		if (listaMensajes == null) {
			listaMensajes = new ArrayList<Mensaje>();
			request.getServletContext().setAttribute("listaMensajes", listaMensajes);
			listaMensajes.add(new Mensaje("Juan Carlos", "Hola chicos, ¿qué tal?"));
		}
		request.getRequestDispatcher("vista.jsp").forward(request, response);
		
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<Mensaje> listaMensajes = (ArrayList<Mensaje>) 
				request.getServletContext().getAttribute("listaMensajes");
		if (request.getParameter("usuario") != "" && request.getParameter("texto") != "") {
			listaMensajes.add(new Mensaje(request.getParameter("usuario"), request.getParameter("texto")));
		}
		request.getRequestDispatcher("vista.jsp").forward(request, response);
		
	}
}

