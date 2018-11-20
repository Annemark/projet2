package fr.dawan.projet2.controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.projet2.dao.ConnectionDB;
import fr.dawan.projet2.dao.ProductDao;

/**
 * Servlet implementation class DownloadController
 */
@WebServlet("/products")
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String urlConfig = "urlFormation";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductsController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			refresh(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void refresh(HttpServletRequest request, HttpServletResponse response) throws Exception{

		String pageParam = request.getParameter("page");
		int page = 1;
		try {
			page = Integer.parseInt(pageParam);
		} catch (Exception e) {
			// nothing, refresh to first page
		}

		String maxParam = request.getParameter("max");
		int max = 5;
		try {
			max = Integer.parseInt(maxParam);
		} catch (Exception e) {
			// nothing, refresh to first page
		}

		int start = (page - 1) * max;

		try {
			Connection cnx = ConnectionDB.getConnection(urlConfig);
			request.setAttribute("listeP", ProductDao.findAll(start, max, cnx, false));

			long nbProducts = ProductDao.nbProducts(cnx, true);
			request.setAttribute("suivExist", (page * max < nbProducts));
			request.setAttribute("page", page);

		} catch (Exception e) {
			MyLogger.myRootLogger.error("Erreur de récupération des produits", e);
		}
		
		request.getRequestDispatcher("products.jsp").forward(request, response);
	}
}
