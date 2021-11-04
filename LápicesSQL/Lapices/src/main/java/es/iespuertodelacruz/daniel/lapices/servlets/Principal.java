package es.iespuertodelacruz.daniel.lapices.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String marca = (String) request.getParameter("marca");
		ArrayList<Lapiz> lapicesList = new ArrayList<>();
		LapicesDAO lapicesDao = new LapicesDAO();
		lapicesList = lapicesDao.buscarPorMarca(marca);
		request.getSession().setAttribute("lapicesList", lapicesList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
