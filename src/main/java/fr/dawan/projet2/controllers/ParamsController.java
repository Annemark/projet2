package fr.dawan.projet2.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParamsServlet
 */
@WebServlet("/params-servlet")
public class ParamsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParamsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		
		response.setContentType("text/html"); // par défaut
		PrintWriter pw = response.getWriter();
		pw.write("<html><body>");
		// response.getOutputStream() : écriture d'un flux
		
		// récup des params
		String p1 = request.getParameter("action");
		if (p1 != null) {
//			response.getWriter().append("<br/> action demandée : " + p1);
			pw.write("<br/> action demandée : <b>" + p1 + "</b><br/>");
		}

		pw.write("<h2>Headers véhiculés dans la requête</h2>");
		Enumeration<String> headersName = request.getHeaderNames();
		
		while (headersName.hasMoreElements()) {
			String n = headersName.nextElement();
			pw.write(n + " = " + request.getHeader(n) + "<BR />");
		}
		
		String[] numValues = request.getParameterValues("num");
		if (numValues != null) {
			pw.write(String.join(" - ", numValues));
			pw.write("<BR />");
		}
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		pw.write("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
