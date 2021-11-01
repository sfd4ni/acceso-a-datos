package es.iespuertodelacruz.dbr.matriculas.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.dbr.matriculas.dao.ManejarFicheros;



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
		request.getRequestDispatcher("vista.jsp").forward(request, response);
	}
	
	
	public void gestionarApuestas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*String realPath = request.getServletContext().getRealPath(File.separator);
		
		String publicFolder = realPath + "publicfolder" 
		+ File.separator + "fichero.txt";
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
		Long horaUltimoSecreto = 
				(Long) request.getServletContext().getAttribute("horaGanador");
		
		Integer secreto = (Integer) request.getServletContext().getAttribute("secreto");
		Long horaSecreto = (Long) request.getServletContext().getAttribute("horaSecreto");
		
		TreeMap<Long, Integer> mapaApuestas = (TreeMap<Long, Integer>) 
				request.getSession().getAttribute("mapaApuestas");
		
		if (secreto == null) {
			Random random = new Random();
			secreto = random.nextInt(10000);
			horaSecreto = new Date().getTime();
			request.getServletContext().setAttribute("secreto", secreto);
			request.getServletContext().setAttribute("horaSecreto", horaSecreto);
		}
		
		if (mapaApuestas == null) {
			mapaApuestas = new TreeMap<>();
		}
		
		if (horaUltimoSecreto != null) {
			if (horaUltimoSecreto < horaSecreto) {
				Iterator<Entry<Long, Integer>> i = mapaApuestas.entrySet().iterator();
				Entry<Long, Integer> e;
				while (i.hasNext() && (e = i.next()) != null) {
				    if (e.getKey() < horaSecreto) {
				         i.remove();
				    }
				}
			}
		}
		
		
		
		String strApuesta =  request.getParameter("apuesta");
		
		
		if(strApuesta != null) {
			Integer apuesta = Integer.parseInt(strApuesta);
			Long horaApuesta = (new Date()).getTime();
			if (secreto.equals(apuesta)) {
				String horaGanador = (new Date().getHours() + ":" + 
			new Date().getMinutes() + ":" + new Date().getSeconds());
				ManejarFicheros mf = new ManejarFicheros("C:\\Users\\Danieldb\\Desktop\\secreto.txt");
				mf.guardarSecreto( (String) request.getSession().getAttribute("usuario"), 
						secreto, ((horaApuesta - (Long) request.getServletContext().getAttribute("horaSecreto")) / 1000));
				request.getServletContext().setAttribute("ganador", 
						request.getSession().getAttribute("usuario"));
				request.getServletContext().setAttribute("horaGanador", 
						horaApuesta);
				request.getServletContext().setAttribute("secretoGanador", secreto);
				Random randomNuevo = new Random();
				secreto = randomNuevo.nextInt(10000);
				horaSecreto = new Date().getTime();
				request.getServletContext().setAttribute("secreto", secreto);
				request.getServletContext().setAttribute("horaSecreto", horaSecreto);
			} else {
				mapaApuestas.put(horaApuesta, apuesta);
			}
			request.getSession().setAttribute("mapaApuestas", mapaApuestas);
		}
	}
	
}

