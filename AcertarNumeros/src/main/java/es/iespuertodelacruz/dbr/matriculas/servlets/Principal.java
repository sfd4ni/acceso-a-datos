package es.iespuertodelacruz.dbr.matriculas.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

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
    	Object usuarioSesion = request.getSession().getAttribute("usuario");
		if (usuarioSesion == null) {
			request.getRequestDispatcher("inicio.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("vista.jsp").forward(request, response);
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HashSet<String> usuariosUsados = (HashSet<String>)request.getSession().getAttribute("usuariosUsados");
		
		if (usuariosUsados == null) {
			usuariosUsados = new HashSet<>();
		}

		Object usuarioSesion = request.getSession().getAttribute("usuario");
		if (usuarioSesion == null) {
			
			String usuario = request.getParameter("usuario");
			
			if (usuario != null) {
				if (!usuariosUsados.contains(usuario)) {
					request.getSession().setAttribute("usuario", usuario);
					usuarioSesion = request.getSession().getAttribute("usuario");
					usuariosUsados.add(usuario);
				} else {
					response.sendRedirect("inicio.jsp");
				}
			}
		} else {
			gestionarApuestas(request, response);
		}
			
		/*ArrayList<Mensaje> listaMensajes = (ArrayList<Mensaje>) 
				request.getServletContext().getAttribute("listaMensajes");
		if ((String) usuarioSesion != "" && request.getParameter("texto") != "") {
			listaMensajes.add(new Mensaje( (String) usuarioSesion, request.getParameter("texto")));
		}
		request.getRequestDispatcher("vista.jsp").forward(request, response);*/
		
	}
	public void gestionarApuestas(HttpServletRequest request, HttpServletResponse response) {
		Integer secreto = (Integer) request.getServletContext().getAttribute("secreto");
		
		Long horaSecreto = (Long) request.getServletContext().getAttribute("horaSecreto");
		
		String strApuesta = request.getParameter("apuesta");
		Integer apuesta = null;
		Long horaApuesta = (new Date()).getTime();
		
		
	}
	
	/*public void eliminarApuestas() {
		Iterator<Entry<Long, Integer>> iterator = apuestas.entrySet(iterator);
		while(iterator.hasNext()) {
			Entry entry = iterator.getNext();
			
		}
	}*/
}

