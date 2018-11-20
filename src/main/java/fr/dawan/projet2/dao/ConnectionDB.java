package fr.dawan.projet2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDB {
	public static Connection getConnection(String urlConf) throws Exception {
		//*******************************
		// CONNEXION DEPUIS L'APPLICATION
		//*******************************
		
		Properties p = new Properties();
		p.load(Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("conf.properties"));
		
		// conf.dev.properties
		// conf.recette.properties
		// conf.prod.properties
		// conf.properties
		
		Class.forName(p.getProperty("driver"));
		
		Connection cnx = DriverManager.getConnection(
				p.getProperty(urlConf), 
				p.getProperty("user"), 
				p.getProperty("pwd"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		//*******************************
		// CONNEXION DEPUIS LE SERVER
		//*******************************
		
//		Context initContext = new InitialContext();
//		// java:/comp/env : noeud  de l'arbre JNDI contenant la datasource
//		Context envContext  = (Context)initContext.lookup("java:/comp/env");
//		DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB");
//		Connection cnx = ds.getConnection();
		return cnx;
		
	}
}
