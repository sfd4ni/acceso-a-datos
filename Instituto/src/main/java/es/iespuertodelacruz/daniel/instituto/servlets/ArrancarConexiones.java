package es.iespuertodelacruz.daniel.instituto.servlets;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import es.iespuertodelacruz.daniel.instituto.dao.GestorConexionesDDBB;

/**
 * Application Lifecycle Listener implementation class ArrancarConexiones
 *
 */
@WebListener
public class ArrancarConexiones implements ServletContextListener, ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public ArrancarConexiones() {
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
         // TODO Auto-generated method stub
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
    	GestorConexionesDDBB gc = new GestorConexionesDDBB("instituto", "root", null);
    	sce.getServletContext().setAttribute("gc", gc);
    }
	
}
