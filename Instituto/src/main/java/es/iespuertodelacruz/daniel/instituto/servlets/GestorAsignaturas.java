package es.iespuertodelacruz.daniel.instituto.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.instituto.dao.AlumnoDAO;
import es.iespuertodelacruz.daniel.instituto.dao.AsignaturaDAO;
import es.iespuertodelacruz.daniel.instituto.dao.GestorConexionesDDBB;
import es.iespuertodelacruz.daniel.instituto.modelo.Asignatura;

/**
 * Servlet implementation class GestorAsignaturas
 */
public class GestorAsignaturas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestorAsignaturas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("asignaturas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonParam = request.getParameter("button");
		AsignaturaDAO asignaturaDao = new AsignaturaDAO((GestorConexionesDDBB)request.getServletContext().getAttribute("gc"));
		System.out.println(buttonParam);
		if(buttonParam.equals("asignaturas")) {
			request.getSession().setAttribute("listaAsignaturas", asignaturaDao.findAll());
			request.getRequestDispatcher("asignaturas.jsp").forward(request, response);
		}
	}

}
