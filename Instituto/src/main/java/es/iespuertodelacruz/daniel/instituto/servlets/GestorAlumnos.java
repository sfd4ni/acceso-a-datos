package es.iespuertodelacruz.daniel.instituto.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			String dni = (String) request.getParameter("dniAgregar");
			String nombre = (String) request.getParameter("nombreAgregar");
			String apellidos = (String) request.getParameter("apellidosAgregar");
			String fecha = request.getParameter("nacimientoAgregar");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = format.parse(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (dni != null && !dni.isEmpty() &&
					nombre != null && !nombre.isEmpty() &&
					apellidos != null && !apellidos.isEmpty() &&
					date != null && date.getTime() > 0) {
				Alumno alumnoAgregar = new Alumno(
						request.getParameter("dniAgregar"),
						request.getParameter("nombreAgregar"),
						request.getParameter("apellidosAgregar"),
						date
						);
				alumnoDao.save(alumnoAgregar);
			}
			request.getSession().setAttribute("listaAlumnos", alumnoDao.findAll());
			request.getRequestDispatcher("alumnos.jsp").forward(request, response);
			
		} else if(buttonParam.equals("alumnos")) {
			request.getSession().setAttribute("listaAlumnos", alumnoDao.findAll());
			request.getRequestDispatcher("alumnos.jsp").forward(request, response);
			
		} else if (buttonParam.equals("mostrar")){
			String dni = (String) request.getParameter("dniMostrar");
			String nombre = (String) request.getParameter("nombreMostrar");
			if (!dni.isEmpty() && nombre.isEmpty()) {
				ArrayList <Alumno> listaAlumnos = new ArrayList<>();
				Alumno alumno = alumnoDao.findById(dni);
				
				if (alumno != null) {
					listaAlumnos.add(alumno);
					request.getSession().setAttribute("listaAlumnos", listaAlumnos);
				}
			} else if (dni.isEmpty() && !nombre.isEmpty()) {
				ArrayList <Alumno> listaAlumnos = null;
				listaAlumnos = alumnoDao.findByNombre(nombre);		
				if (listaAlumnos != null) {
					request.getSession().setAttribute("listaAlumnos", listaAlumnos);
				}
			}
			request.getRequestDispatcher("alumnos.jsp").forward(request, response);
		} else if (buttonParam.equals("borrar")) {
			String dni = (String) request.getParameter("dniBorrar");
			if (dni != null) {
				alumnoDao.delete(dni);
				request.getSession().setAttribute("listaAlumnos", alumnoDao.findAll());
				request.getRequestDispatcher("alumnos.jsp").forward(request, response);
			}
		} else if (buttonParam.equals("editar")) {
			String dni = (String) request.getParameter("dniEditar");
			String nombre = (String) request.getParameter("nombreEditar");
			String apellidos = (String) request.getParameter("apellidosEditar");
			String fecha = request.getParameter("nacimientoEditar");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = format.parse(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (dni != null && !dni.isEmpty() &&
					nombre != null && !nombre.isEmpty() &&
					apellidos != null && !apellidos.isEmpty() &&
					date != null && date.getTime() > 0) {
				System.out.println("Llegué");
				Alumno alumnoEditar = new Alumno(
						dni,
						nombre,
						apellidos,
						date
						);
				alumnoDao.update(alumnoEditar);
			}
			request.getSession().setAttribute("listaAlumnos", alumnoDao.findAll());
			request.getRequestDispatcher("alumnos.jsp").forward(request, response);
		}
	}

}
