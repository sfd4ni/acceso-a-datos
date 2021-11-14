package es.iespuertodelacruz.daniel.instituto.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.instituto.dao.AlumnoDAO;
import es.iespuertodelacruz.daniel.instituto.dao.AsignaturaDAO;
import es.iespuertodelacruz.daniel.instituto.dao.GestorConexionesDDBB;
import es.iespuertodelacruz.daniel.instituto.dao.MatriculaDAO;
import es.iespuertodelacruz.daniel.instituto.modelo.Alumno;
import es.iespuertodelacruz.daniel.instituto.modelo.Asignatura;
import es.iespuertodelacruz.daniel.instituto.modelo.Matricula;

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
		AlumnoDAO alumnoDao = new AlumnoDAO((GestorConexionesDDBB)request.getServletContext().getAttribute("gc"));
		AsignaturaDAO asignaturaDao = new AsignaturaDAO((GestorConexionesDDBB)request.getServletContext().getAttribute("gc"));
		
		switch (buttonParam) {
		
			case "matriculas":
				request.getSession().setAttribute("listaMatriculas", matriculaDao.findAll());
				break;
				
			case "borrar":
				Integer idBorrar = null;
				try {
					idBorrar = Integer.parseInt(request.getParameter("idMatriculaBorrar"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (idBorrar != null) {
					matriculaDao.delete(idBorrar);
				}
				request.getSession().setAttribute("listaMatriculas", matriculaDao.findAll());
				break;
				
			case "agregar":
				
				Integer yearAgregar = null;
				try {
					yearAgregar = Integer.parseInt(request.getParameter("yearAgregar"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				String dniAgregar= request.getParameter("dniAgregar");
				String [] asignaturasAgregar = request.getParameter("asignaturasAgregar").split(",");
				if (yearAgregar != null
						&& !dniAgregar.isEmpty() && asignaturasAgregar.length > 0) {
					Alumno alumnoAgregar = alumnoDao.findById(dniAgregar);
					ArrayList<Asignatura> listaAsignaturasAgregar = new ArrayList<>();
					for (String asignaturaStr : asignaturasAgregar) {
						String asignaturaTratar = asignaturaStr.trim();
						Asignatura asignatura = asignaturaDao.findByName(asignaturaTratar);
						if (asignatura != null) {
							listaAsignaturasAgregar.add(asignatura);
						}
					}
					if (alumnoAgregar != null && !listaAsignaturasAgregar.isEmpty()) {
						Matricula matriculaAgregar = new Matricula(yearAgregar);
						matriculaAgregar.setAlumno(alumnoAgregar);
						matriculaAgregar.setYear(yearAgregar);
						matriculaAgregar.setAsignaturas(listaAsignaturasAgregar);
						matriculaDao.save(matriculaAgregar);
					}
					
				}
				request.getSession().setAttribute("listaMatriculas", matriculaDao.findAll());
				break;
				
			case "mostrar":
				
				Integer yearMostrar = null;
				try {
					if (!request.getParameter("yearMostrar").isEmpty())
						yearMostrar = Integer.parseInt(request.getParameter("yearMostrar"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				String dniMostrar = request.getParameter("dniMostrar");
				
				if (dniMostrar.isEmpty() && yearMostrar != null) {
					ArrayList<Matricula> listaMatriculasMostrar = matriculaDao.findByYear(yearMostrar);
					if (listaMatriculasMostrar != null) {
						request.setAttribute("listaMatriculas", listaMatriculasMostrar);
					}
				} else if (!dniMostrar.isEmpty() && yearMostrar == null) {
					ArrayList<Matricula> listaMatriculasMostrar = new ArrayList<>();
					if (matriculaDao.findByDni(dniMostrar) != null) {
						listaMatriculasMostrar.add(matriculaDao.findByDni(dniMostrar));
						request.setAttribute("listaMatriculas", listaMatriculasMostrar);
					}
				}
				break;
				
			case "editar":
				Integer yearEditar = null;
				Integer idEditar = null;
				try {
					yearEditar = Integer.parseInt(request.getParameter("yearEditar"));
					idEditar = Integer.parseInt(request.getParameter("idMatriculaEditar"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				String dniEditar= request.getParameter("dniEditar");
				String [] asignaturasEditar = request.getParameter("asignaturasEditar").split(",");
				if (yearEditar != null && idEditar != null
						&& !dniEditar.isEmpty() && asignaturasEditar.length > 0) {
					Alumno alumnoEditar = alumnoDao.findById(dniEditar);
					ArrayList<Asignatura> listaAsignaturasEditar = new ArrayList<>();
					for (String asignaturaStr : asignaturasEditar) {
						String asignaturaTratar = asignaturaStr.trim();
						Asignatura asignatura = asignaturaDao.findByName(asignaturaTratar);
						if (asignatura != null) {
							listaAsignaturasEditar.add(asignatura);
						}
					}
					if (alumnoEditar != null && !listaAsignaturasEditar.isEmpty()) {
						Matricula matriculaEditar = new Matricula(yearEditar);
						matriculaEditar.setAlumno(alumnoEditar);
						matriculaEditar.setYear(yearEditar);
						matriculaEditar.setAsignaturas(listaAsignaturasEditar);
						matriculaEditar.setIdmatricula(idEditar);
						matriculaDao.update(matriculaEditar);
					}
					
				}
				request.getSession().setAttribute("listaMatriculas", matriculaDao.findAll());
				break;
				
			default:
				break;
		}
		request.getRequestDispatcher("matriculas.jsp").forward(request, response);
	}

}
