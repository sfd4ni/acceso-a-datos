package es.iespuertodelacruz.daniel.lapices.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.daniel.lapices.dao.GestorLapices;
import es.iespuertodelacruz.daniel.lapices.dao.LapicesDAO;
import es.iespuertodelacruz.daniel.lapices.modelo.Lapiz;

/**
 * Servlet implementation class Principal
 */
@WebServlet("/Principal, /principal")
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Principal() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonParam = request.getParameter("button");
		GestorLapices gestorLapices = new GestorLapices("oficina");
		if(buttonParam.equals("mostrar")) {
			String marca = (String) request.getParameter("marca");
			ArrayList<Lapiz> lapicesList = new ArrayList<>();
			lapicesList = gestorLapices.buscarPorMarca(marca);
			request.getSession().setAttribute("lapicesList", lapicesList);
		} else if (buttonParam.equals("borrar")) {
			Integer id = Integer.parseInt(request.getParameter("idBorrar"));
			gestorLapices.borrarLapiz(id);
		} else if (buttonParam.equals("editar")) {
			Lapiz lapizModificar = new Lapiz(
					Integer.parseInt(request.getParameter("idEditar")),
					request.getParameter("marcaEditar"),
					Integer.parseInt(request.getParameter("numeroEditar"))
					);
			gestorLapices.actualizarLapiz(lapizModificar, Integer.parseInt(request.getParameter("idEditar")));
		} else if (buttonParam.equals("agregar")) {
			Lapiz lapizAgregar = new Lapiz(
					Integer.parseInt(request.getParameter("idAgregar")),
					request.getParameter("marcaAgregar"),
					Integer.parseInt(request.getParameter("numeroAgregar"))
					);
			try {
				gestorLapices.saveLapiz(lapizAgregar);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
