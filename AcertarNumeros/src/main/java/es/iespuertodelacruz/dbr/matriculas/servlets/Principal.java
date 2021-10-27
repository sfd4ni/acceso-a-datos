package es.iespuertodelacruz.dbr.matriculas.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.dbr.matriculas.modelo.Mensaje;



public class Principal extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    public Principal() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse 
    		response) throws ServletException, IOException {
    	Object usuarioSesion = request.getSession().getAttribute("usuario");
		if (usuarioSesion == null) {
			request.getRequestDispatcher("inicio.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("vista.jsp").forward(request, response);
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("vista.jsp").forward(request, response);
		request.setCharacterEncoding("UTF-8");
		
		HashSet<String> usuariosUsados = (HashSet<String>)request.getSession().getAttribute("usuariosUsados");
		
		if (usuariosUsados == null) {
			usuariosUsados = new HashSet<>();
		}

		Object usuarioSesion = request.getSession().getAttribute("usuario");
		if (usuarioSesion == null) {
			
			String usuario = request.getParameter("usuario");
			
			if (usuario != null) {
				if (!usuariosUsados.contains(usuario)) {
					request.getSession().setAttribute("usuario", usuario);
					usuarioSesion = request.getSession().getAttribute("usuario");
					usuariosUsados.add(usuario);
				} else {
					response.sendRedirect("inicio.jsp");
				}
			}
		} else {
			gestionarApuestas(request, response);
		}
		
	}
	public void gestionarApuestas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*String realPath = request.getServletContext().getRealPath(File.separator);
		
		String publicFolder = realPath + "publicfolder" 
		+ File.separator + "fichero.txt";
		*/
		File directorio = new File("/tmp/" + request.getSession().getAttribute("usuario"));
		if(!directorio.exists()) {
			directorio.mkdir();
		}
		/*
		String realPath = request.getServletContext().getRealPath(File.separator);
		
		String publicFolder = realPath + "publicfolder" 
		+ File.separator + "fichero.txt";
		
		String privatefolder = realPath + "WEB-INF" 
		+ File.separator + "privatefolder" + File.separator + "fichero.txt";
		*/
		Integer secreto = (Integer) request.getSession().getAttribute("secreto");
		Long horaSecreto = (Long) request.getSession().getAttribute("horaSecreto");
		
		if (secreto == null) {
			Random random = new Random();
			secreto = random.nextInt(10000);
			System.out.println(secreto);
			horaSecreto = new Date().getTime();
			request.getSession().setAttribute("secreto", secreto);
			request.getSession().setAttribute("horaSecreto", horaSecreto);
		}
		
		TreeMap<Long, Integer> mapaApuestas = (TreeMap<Long, Integer>) 
				request.getServletContext().getAttribute("mapaApuestas");
		
		if (mapaApuestas == null) {
			mapaApuestas = new TreeMap<>();
		}
		
		String strApuesta = request.getParameter("apuesta");
		
		
		if(strApuesta != null) {
			Integer apuesta = Integer.parseInt(strApuesta);
			Long horaApuesta = (new Date()).getTime();
			System.out.println(secreto);
			System.out.println(secreto==apuesta);
			if (secreto == apuesta) {
				request.getServletContext().setAttribute("ganador", 
						request.getSession().getAttribute("usuario"));
				request.getServletContext().setAttribute("horaGanador", 
						horaApuesta);
				request.getServletContext().setAttribute("secretoGanador", secreto);
				for (Entry<Long, Integer> entry : mapaApuestas.entrySet()) {
					if (entry.getKey() < horaApuesta) {
						mapaApuestas.remove(entry);
					}
				}
			} else {
				mapaApuestas.put(horaApuesta, apuesta);
			}
			request.getServletContext().setAttribute("mapaApuestas", mapaApuestas);
		}
		
	}
	
}

