package es.iespuertodelacruz.jc.matriculas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConsultarNumero
 */
@WebServlet({ "/ConsultarNumero", "/consultarnumero" })
public class ConsultarNumero extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarNumero() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//tomamos la información que nos envíe el usuario
		//esto lo hace siempre mediante parámetros
		//observar que estamos en el método doPost() porque
		//esperamos que nos lo envíe con un formulario POST
		String strNumero = request.getParameter("numero");
		
		Integer numero = null;
		try {
			numero =Integer.parseInt(strNumero);
		}catch(Exception ex) {}
		
		if(numero != null) {
			//Tomamos del ámbito de aplicación la lista de números premiados
			Object o = request.getServletContext().getAttribute("listapremiados");
			if( o != null) {
				HashSet<Integer> listaPremiados = (HashSet<Integer> )o;
				
				//buscamos el número entre los premiados:
				boolean numeroEsPremiado = listaPremiados.contains(numero);
				
				
		        response.setContentType("text/html;charset=UTF-8");
		        try (PrintWriter out = response.getWriter()) {
		            /* TODO output your page here. You may use following sample code. */
		            out.println("<!DOCTYPE html>");
		            out.println("<html>");
		            out.println("<head>");
		            out.println("<title>Servlet NewServlet</title>");            
		            out.println("</head>");
		            out.println("<body>");
		            out.println("<h1>Respuesta a consulta número </h1>");
		            if( numeroEsPremiado) {
		            	out.println("<p>felicidades! " + numero + " ha sido premiado</p>");
		            }else {
		            	out.println("<p>lo lamentamos... " + numero + " no ha sido premiado</p>");
		            }
		            out.println("<a href='inicio' >pulsa para regresar </a>");
		            out.println("</body>");
		            out.println("</html>");
		        }
					
			}
		}
	}

}
