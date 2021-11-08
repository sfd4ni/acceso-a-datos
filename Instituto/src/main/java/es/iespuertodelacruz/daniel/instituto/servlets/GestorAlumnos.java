package es.iespuertodelacruz.daniel.instituto.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.instituto.dao.AlumnoDAO;
import es.iespuertodelacruz.daniel.instituto.dao.GestorConexionesDDBB;
import es.iespuertodelacruz.daniel.instituto.modelo.Alumno;

/**
 * Servlet implementation class GestorAlumnos
 */
public class GestorAlumnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestorAlumnos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("alumnos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String buttonParam = request.getParameter("button");
		AlumnoDAO alumnoDao = new AlumnoDAO((GestorConexionesDDBB)request.getServletContext().getAttribute("gc"));
		if(buttonParam.equals("agregar")) {
			String fecha = request.getParameter("nacimientoAgregar");
			SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
			Date date = null;
			
			try {
				date = format.parse(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Alumno alumnoAgregar = new Alumno(
					request.getParameter("dniAgregar"),
					request.getParameter("nombreAgregar"),
					request.getParameter("apellidosAgregar"),
					date
					);
			alumnoDao.save(alumnoAgregar);
			request.getRequestDispatcher("alumnos.jsp").forward(request, response);
		} else if(buttonParam.equals("matriculas")) {
			request.getRequestDispatcher("gestormatriculas").forward(request, response);
		} else {
			request.getRequestDispatcher("gestormatriculas").forward(request, response);
		}
	}

}
