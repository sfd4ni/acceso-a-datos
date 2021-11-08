package es.iespuertodelacruz.daniel.instituto.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.instituto.dao.AlumnoDAO;
import es.iespuertodelacruz.daniel.instituto.dao.GestorConexionesDDBB;
import es.iespuertodelacruz.daniel.instituto.modelo.Alumno;
import es.iespuertodelacruz.daniel.lapices.modelo.Lapiz;

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
		AlumnoDAO alumnoDao = new AlumnoDAO((GestorConexionesDDBB)request.getSession().getAttribute("gc"));
		if(buttonParam.equals("agregar")) {
			String fecha = request.getParameter("fechaAgregar");
			String[] fechaIntrod = fecha.split("/");
			Date fechaInt = new Date(Integer.parseInt(fechaIntrod[0]), Integer.parseInt(fechaIntrod[1]), Integer.parseInt(fechaIntrod[2]));
			Alumno alumnoAgregar = new Alumno(
					request.getParameter("dniAgregar"),
					request.getParameter("nombreAgregar"),
					request.getParameter("apellidosAgregar"),
					fechaInt
					);
			alumnoDao.save(alumnoAgregar);
			request.getRequestDispatcher("alumnos.jsp").forward(request, response);
		} else if(buttonParam.equals("matriculas")) {
			request.getRequestDispatcher("gestormatriculas").forward(request, response);
		} else {
			request.getRequestDispatcher("asignaturas.jsp").forward(request, response);
		}
	}

}
