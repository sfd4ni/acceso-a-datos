package es.iespuertodelacruz.jc.matriculas.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	ArrayList<String> listaMensajes = (ArrayList<String>) 
			request.getServletContext().getAttribute("mensajes");
	if (listaMensajes == null) {
		listaMensajes = new ArrayList<String>();
		request.getServletContext().setAttribute("mensajes", listaMensajes);
		for(int i = 0; i < 4; i++) {
			listaMensajes.add("Iteracion " + i);
		}
	}
	request.getRequestDispatcher("vista.jsp").forward(request, response);
	
    }
}

