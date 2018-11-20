package fr.dawan.projet2.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
public class MySessionListener implements HttpSessionListener {
	/**
	 * Default constructor.
	 */
	public MySessionListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {

		MyLogger.myRootLogger.info("Nouvelle session ouverte depuis " + se.getSession().getId());

		ServletContext application = se.getSession().getServletContext();
		Object nbVisitors = application.getAttribute("nbVisitors");
		if (nbVisitors == null) {
			application.setAttribute("nbVisitors", 1);
		} else {
			int nbVisitor = Integer.parseInt(nbVisitors.toString());
			application.setAttribute("nbVisitors", ++nbVisitor);
		}
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext application = se.getSession().getServletContext();
		Object nbVisitors = application.getAttribute("nbVisitors");
		if (nbVisitors != null) {
			int nbVisitor = Integer.parseInt(nbVisitors.toString());
			application.setAttribute("nbVisitors", --nbVisitor);
		}
	}

}
