package es.iespuertodelacruz.daniel.instituto.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.instituto.dao.GestorConexionesDDBB;
import es.iespuertodelacruz.daniel.instituto.dao.MatriculaDAO;

/**
 * Servlet implementation class GestorMatriculas
 */
public class GestorMatriculas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestorMatriculas() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("matriculas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonParam = request.getParameter("button");
		MatriculaDAO matriculaDao = new MatriculaDAO((GestorConexionesDDBB)request.getServletContext().getAttribute("gc"));
		switch (buttonParam) {
			case "matriculas":
				request.getSession().setAttribute("listaMatriculas", matriculaDao.findAll());
				break;
			case "borrar":
				break;
			case "agregar":
				break;
			case "mostrar":
				break;
			case "editar":
				break;
			default:
				
				break;
		}
		request.getRequestDispatcher("matriculas.jsp").forward(request, response);
	}

}
