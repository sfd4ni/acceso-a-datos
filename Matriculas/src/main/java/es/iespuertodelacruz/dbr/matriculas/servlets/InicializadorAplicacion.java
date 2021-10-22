package es.iespuertodelacruz.dbr.matriculas.servlets;

import java.util.ArrayList;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import es.iespuertodelacruz.dbr.matriculas.dao.*;
/**
 * Application Lifecycle Listener implementation class InicializadorAplicacion
 *
 */
@WebListener
public class InicializadorAplicacion implements ServletContextListener, ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public InicializadorAplicacion() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         ManejarFicheros mf = (ManejarFicheros) sce.getServletContext().getAttribute("manejarFichero");
         mf.guardarTodo((ArrayList<String>) sce.getServletContext().getAttribute("listaMensajes"));
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         ManejarFicheros mf = new ManejarFicheros("/tmp/mensajes.txt");
         sce.getServletContext().setAttribute("manejarFichero", mf);
    }
	
}
