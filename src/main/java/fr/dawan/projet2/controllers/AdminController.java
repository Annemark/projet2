package fr.dawan.projet2.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.projet2.beans.Contact;
import fr.dawan.projet2.dao.ContactDao;

/**
 * Servlet implementation class DownloadController
 */
//@WebServlet("/admin")
@WebServlet(urlPatterns= {"/admin" }, initParams = {
		@WebInitParam(name="annee", value="2018"),
		@WebInitParam(name="test", value="toto"),
})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action = " + action);
		
		if (action != null) {
			switch (action) {
			case "login":
				showLoginForm(request, response);
				break;
			case "remove":
				removeContact(request, response);
				break;
			case "edit":
				updateContact(request, response);
				break;
			case "exportcsv":
				exportCSV(request, response);
				break;
			case "disconnect":
				disconnect(request, response);
				break;
			default:
				request.setAttribute("msg", "Paramètre incorrect");
				Refresh(request, response);

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "connect":
				checkLogin(request, response);
				break;
			case "save":
				saveContact(request, response);
				break;
			}
		}
	}

	private void disconnect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("/").forward(request, response);
	}

	private void exportCSV(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachement;filename=\"export" + new Date().getTime() + ".csv\"");

		ServletOutputStream out = response.getOutputStream();

		for (Contact c : ContactDao.findAll()) {
			out.write((c.getId() + ";" + c.getName() + ";" + c.getEmail() + ";" + c.getPassword() + "\n").getBytes());
			// Faire un out.flush(); dans le cas de fichiers conséquents (n fois)
		}

		out.close();
	}

	private void Refresh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listeC", ContactDao.findAll());
		request.getRequestDispatcher("backoffice/admin.jsp").forward(request, response);
	}

	private void updateContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			Contact c = ContactDao.find(Integer.parseInt(id));
			if (c != null)
				request.setAttribute("updatedContact", c);
		}

		Refresh(request, response);

	}

	private void removeContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			int result = ContactDao.remove(Integer.parseInt(id));
			if (result == -1)
				request.setAttribute("msg", "Erreur : suppression impossible");
		}

		Refresh(request, response);

	}

	private void saveContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("save " + id);
		int result;

		try {
			Contact c = new Contact();

			// TODO : check parameters
			c.setName(request.getParameter("name"));
			c.setPassword(request.getParameter("password"));
			c.setEmail(request.getParameter("email"));

			if (id != null && id != "") {
				c.setId(Integer.parseInt(id));
				result = ContactDao.update(c);
			} else {
				c.setId(++ContactDao.nb);
				result = ContactDao.add(c);
			}

			if (result == -1)
				request.setAttribute("msg", "Erreur : suppression impossible");
		} catch (NumberFormatException e) {
			MyLogger.myRootLogger.error("Nombre non valide" , e);
		} catch (Exception e) {
			MyLogger.myRootLogger.error("Erreur saveContact" , e);
		}

		Refresh(request, response);

	}

	private void showLoginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cible = "login.jsp";

		request.setAttribute("monAnnee", getInitParameter("annee"));
		request.setAttribute("authorName", getServletContext().getInitParameter("author"));
		
		if (email != null && password != null) {
			Contact c = ContactDao.find(email);
			if (c != null) {
				if (!password.equals(c.getPassword())) {
					request.setAttribute("msg", "Mauvais mot de passe");
				} else {
					request.getSession().setAttribute("userName", c.getName());
					request.setAttribute("listeC", ContactDao.findAll());
					
					Object obj = request.getSession().getAttribute("requestedURI");
					if (obj == null) cible = "backoffice/admin.jsp";
					else {
						String uri = obj.toString();
						cible = uri.substring(request.getContextPath().length() + 1, uri.length());
					}
					
					System.out.println("cible : " + cible);
				}
			} else {
				request.setAttribute("msg", "Identifiants invalides");
			}

			request.getRequestDispatcher(cible).forward(request, response);
		}

	}

}
