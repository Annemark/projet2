package fr.dawan.projet2.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.projet2.beans.Basket;

/**
 * Servlet implementation class DownloadController
 */
@WebServlet("/basket")
public class BasketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BasketController() {
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
				case "changeqty":
					changeQty(request, response);
					break;
				case "remove":
					remove(request, response);
					break;
				default:
					request.setAttribute("msg", "Paramètre incorrect");
					Refresh(request, response);

			}
		}
		else {
			Refresh(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Basket basket = getBasket(request);
		int num = Integer.parseInt(request.getParameter("num"));
		
		if (basket != null) {
			basket.getLines().remove(num);
		}
		
		Refresh(request, response);
	}

	private void changeQty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		int quantity = Integer.parseInt(request.getParameter("qte"));
		
		if (quantity >= 0) {
			Basket basket = getBasket(request);
			basket.getLines().get(num).setQuantity(quantity);
		}
		
		Refresh(request, response);
	}

//	private BasketLine getBasketLine(HttpServletRequest request) {
//		
//		Basket basket = getBasket(request);
//		String id = request.getParameter("id");
//
//		if (basket != null && id != null) {
//			Product product = ProductDao.find(Integer.parseInt(id));
//			BasketLine basketLine = new BasketLine(product, 0);
//			
//			int pos = basket.getLines().indexOf(basketLine);
//			if(pos==-1) return basket.getLines().get(pos);
//		}
//		
//		return null;
//	}
	
	private Basket getBasket(HttpServletRequest request) {
		Object sessionBasket = request.getSession().getAttribute("basket");

		if (sessionBasket != null) {
			return (Basket) sessionBasket;
		}
		
		return null;
	}

	private void Refresh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object sessionBasket = request.getSession().getAttribute("basket");

		if (sessionBasket != null) {
			Basket basket = (Basket) sessionBasket;
			request.setAttribute("totalBasket", basket.getTotal());
			request.setAttribute("listeBL", basket.getLines());
			request.getRequestDispatcher("basket.jsp").forward(request, response);
		}
	}
}
