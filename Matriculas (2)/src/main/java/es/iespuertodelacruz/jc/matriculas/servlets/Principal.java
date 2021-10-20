package es.iespuertodelacruz.jc.matriculas.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Principal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Vamos a emular que el servlet hace consultas a una base de datos
		// y que al final ha obtenido una lista de números premiados en una lotería
		

		//como es información de consulta para todos los usuarios esos números estarán en 
		//el ámbito de aplicación: request.getServletContext()
		//primero vemos si ya está cargada en memoria ( en otro caso se tiene que generar )
		HashSet<Integer> listaPremiados = (HashSet<Integer>)
				request.getServletContext()
					.getAttribute("listapremiados");
		
		//si la lista es == null entonces es que no se ha cargado aún en memoria
		//sería aquí cuando se haría a la base de datos. En lugar de eso
		//nos inventamos los números ( un mínimo de 2 números un máximo de 11 )
		if(listaPremiados == null) {
			listaPremiados = new HashSet<>();
			int cantidadPremios = (int)(10*Math.random() + 2);
			for (int i = 0; listaPremiados.size() < cantidadPremios ; i++) {
				listaPremiados.add( (int)(Math.random()*10));
			}
			
			//ponemos la lista compartida en el espacio de aplicación
			//para que todos los usuarios puedan acceder
			request.getServletContext().setAttribute("listapremiados", listaPremiados);
		}
	
		
		
		request.getRequestDispatcher("inicio.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
