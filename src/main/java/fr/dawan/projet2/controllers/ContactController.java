package fr.dawan.projet2.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Servlet implementation class ContactController
 */
@WebServlet("/contact")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String exp = request.getParameter("expediteur");
		String mailExp = request.getParameter("mailExp");
		String sujet = request.getParameter("sujet");
		String msg = request.getParameter("msg");

		try {
			sendEmail(mailExp, sujet, msg, exp);
			request.setAttribute("resultMsg", "Msg envoyé");

		} catch (EmailException e) {
			request.setAttribute("resultMsg", "Erreur : " + e.getMessage());

		}
		request.getRequestDispatcher("contact.jsp").forward(request, response);
	}

	public void sendEmail(String toEmail, String subject, String body, String exp) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.dsl.ovh.net");
		// email.setSmtpPort(465);
		// email.setAuthenticator(new DefaultAuthenticator("login", "password"));
		// email.setSSLOnConnect(true);
		email.setFrom(exp);
		email.setSubject(subject);
		email.setMsg(body);
		email.addTo(toEmail);
		email.send();
	}

}
