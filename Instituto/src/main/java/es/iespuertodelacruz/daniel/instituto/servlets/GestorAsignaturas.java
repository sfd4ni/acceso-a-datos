package es.iespuertodelacruz.daniel.instituto.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.instituto.dao.AlumnoDAO;
import es.iespuertodelacruz.daniel.instituto.dao.AsignaturaDAO;
import es.iespuertodelacruz.daniel.instituto.dao.GestorConexionesDDBB;
import es.iespuertodelacruz.daniel.instituto.modelo.Alumno;
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
		switch (buttonParam) {
			case "asignaturas":
				request.getSession().setAttribute("listaAsignaturas", asignaturaDao.findAll());
				
				break;
			 case "mostrar":
				 Integer id = null;
				 if (!request.getParameter("idMostrar").isEmpty()) {
					 try {
						 id = Integer.parseInt(request.getParameter("idMostrar"));
					 } catch(Exception e) {
						 e.printStackTrace();
					 }
				 }
					String nombre = (String) request.getParameter("nombreMostrar");
					if (id != null && nombre.isEmpty()) {
						ArrayList <Asignatura> listaAsignaturas = new ArrayList<>();
						Asignatura asignatura = asignaturaDao.findById(id);
						if (asignatura != null) {
							listaAsignaturas.add(asignatura);
							request.getSession().setAttribute("listaAsignaturas", listaAsignaturas);
						}
					} else if (id == null && !nombre.isEmpty()) {
						ArrayList <Asignatura> listaAsignaturas = new ArrayList<>();
						Asignatura asignatura = asignaturaDao.findByName(nombre);	
						if (asignatura != null) {
							listaAsignaturas.add(asignatura);
							request.getSession().setAttribute("listaAsignaturas", listaAsignaturas);
						}
					}
				break;
			 case "borrar":
				 Integer idBorrar = null;
				 if (!request.getParameter("idBorrar").isEmpty()) {
					 try {
						 idBorrar = Integer.parseInt(request.getParameter("idBorrar"));
					 } catch(Exception e) {
						 e.printStackTrace();
					 }
				 }
					if (idBorrar != null) {
						asignaturaDao.delete(idBorrar);
						request.getSession().setAttribute("listaAsignaturas", asignaturaDao.findAll());
					}
				 break;
			 case "editar":
				 Integer idEditar = null;
				 if (!request.getParameter("idEditar").isEmpty()) {
					 try {
						 idEditar = Integer.parseInt(request.getParameter("idEditar"));
					 } catch(Exception e) {
						 e.printStackTrace();
					 }
				 }
				 String nombreEditar = (String) request.getParameter("nombreEditar");
				 String cursoEditar = (String) request.getParameter("cursoEditar");
				 if (idEditar != null && idEditar > 0 &&
						nombreEditar != null && !nombreEditar.isEmpty() &&
						cursoEditar != null && !cursoEditar.isEmpty())
						{
					Asignatura asignaturaEditar = new Asignatura(nombreEditar, cursoEditar);
					asignaturaEditar.setIdasignatura(idEditar);
					asignaturaDao.update(asignaturaEditar);
					request.getSession().setAttribute("listaAsignaturas", asignaturaDao.findAll());
				}
				 break;
			 case "agregar":
				 String  nombreAgregar = request.getParameter("nombreAgregar");
				 String cursoAgregar = request.getParameter("cursoAgregar");
				 if (nombreAgregar != null && !nombreAgregar.isEmpty()
						 && cursoAgregar != null && !cursoAgregar.isEmpty()) {
					 Asignatura asignaturaAgregar = new Asignatura(nombreAgregar, cursoAgregar);
					 asignaturaAgregar = asignaturaDao.save(asignaturaAgregar);
					 request.getSession().setAttribute("listaAsignaturas", asignaturaDao.findAll());
				 }
				 break;
			 default:
				 request.getSession().setAttribute("listaAsignaturas", asignaturaDao.findAll());
			 	break;
			
		}
		request.getRequestDispatcher("asignaturas.jsp").forward(request, response);
	}

}
