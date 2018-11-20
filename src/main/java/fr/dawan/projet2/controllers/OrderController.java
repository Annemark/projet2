package fr.dawan.projet2.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.projet2.beans.Basket;
import fr.dawan.projet2.beans.BasketLine;
import fr.dawan.projet2.beans.Product;
import fr.dawan.projet2.dao.ProductDao;

/**
 * Servlet implementation class DownloadController
 */
@WebServlet("/order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Refresh(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AddProduct(request, response);
	}

	private void AddProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object productParam = request.getParameter("product");

		if (productParam != null) {
			int productId = Integer.parseInt(productParam.toString());

			Object sessionBasket = request.getSession().getAttribute("basket");
			Basket basket = null;

			if (sessionBasket == null) {
				basket = new Basket();
				request.getSession().setAttribute("basket", basket);
			} else {
				basket = (Basket) sessionBasket;
			}

			Product product = ProductDao.find(productId);
			Object qte = request.getParameter("qte");
			
			//_____________________
			
			Cookie ck = new Cookie("myCookie", product.getDescription());
			ck.setMaxAge(60*60*72); //72h
			response.addCookie(ck);
			
			//récup. request.getCookies() => Cookie[]
			
			//_____________________
			
			
			
			if (qte != null) {
			
				int quantity = Integer.parseInt(qte.toString());
				BasketLine basketLine = new BasketLine(product, quantity);
				
				int pos = basket.getLines().indexOf(basketLine);
				if(pos==-1)
					basket.getLines().add(basketLine);
				else {
					BasketLine bl = basket.getLines().get(pos);
					bl.setQuantity(bl.getQuantity() + quantity);
				}
			}

			request.setAttribute("nbProducts", basket.getNbArticle());
		}
		
		Refresh(request, response);
	}

	private void Refresh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listeP", ProductDao.findAll());
		request.getRequestDispatcher("order.jsp").forward(request, response);
	}
}
