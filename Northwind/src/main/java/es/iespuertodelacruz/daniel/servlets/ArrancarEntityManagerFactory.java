package es.iespuertodelacruz.daniel.servlets;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ArrancarEntityManagerFactory
 *
 */
public class ArrancarEntityManagerFactory implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ArrancarEntityManagerFactory() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidadPersistencia");
    	sce.getServletContext().setAttribute("emf", emf);
    }
	
}
