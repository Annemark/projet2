package fr.dawan.projet2.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dawan.projet2.beans.Quiz;
import fr.dawan.projet2.beans.QuizQuestion;
import fr.dawan.projet2.beans.QuizReponse;
import fr.dawan.projet2.dao.ConnectionDB;
import fr.dawan.projet2.dao.QuizDao;
import fr.dawan.projet2.dao.QuizQuestionDao;

/**
 * Servlet implementation class DownloadController
 */
@WebServlet("/quiz")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String urlConfig = "urlQuiz";	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuizController() {
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
			case "start":
				startQuiz(request, response);
				break;
			}

		}
	}
	
	private static final String SESSION_INDEX_QUESTION = "indexQuestion";

	private void startQuiz(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String quizIdStr = request.getParameter("quizId");
		System.out.println(quizIdStr);
		if (quizIdStr != null && !quizIdStr.trim().equals("")) {
			try {
				int id = Integer.parseInt(quizIdStr);

				Connection cnx = ConnectionDB.getConnection(urlConfig);
				HttpSession session = request.getSession();
				session.setAttribute("score", 0);
				session.setAttribute("numQstEnCours", 1);
				session.setAttribute("nbQuestions", QuizDao.nbQuestions(id, cnx, false));
				
				Quiz quiz = QuizDao.findQuizById(id, cnx, false);

				System.out.println(quiz);
				if (quiz != null) {
					request.setAttribute("quiz", quiz);

					displayQuestion(id, cnx, request, response);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher("quiz.jsp").forward(request, response);
	}

	private void displayQuestion(int quizId, Connection cnx, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int numQstEnCours = (Integer) session.getAttribute("numQstEnCours");
			QuizQuestion qx = QuizQuestionDao.findQuestionByQuizId(quizId, numQstEnCours, cnx, false);
			// System.out.println(qx);
			if (qx != null) {
				request.setAttribute("question", qx);

				ArrayList<QuizReponse> reponses = qx.getReponses();
				System.out.println(reponses.size());

				request.setAttribute("reponses", reponses);
				request.setAttribute("quiz", QuizDao.findQuizById(quizId, cnx, true));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String cible = "/quiz.jsp";
		
		HttpSession session = request.getSession();
		try {
			int numQstEnCours = (Integer) session.getAttribute("numQstEnCours");
			int nbQuestions = (Integer) session.getAttribute("nbQuestions");
			int quizId = Integer.parseInt(request.getParameter("quizId"));
			int questionId = Integer.parseInt(request.getParameter("questionId"));
			int score = (int)session.getAttribute("score");
			
			Connection cnx = ConnectionDB.getConnection(urlConfig);
			
			//TODO calcul score
			QuizQuestion qx = QuizQuestionDao.findQuestionById(questionId, cnx, false);
			if(qx.isMultipleChoice()) {
				String[] results = request.getParameterValues("reponse");
				if (results != null) {
					for (String selectedRespId : results) {
						QuizReponse reponse = QuizDao.findResponseById(Integer.parseInt(selectedRespId), cnx, false);
						score += reponse.isCorrect() ? 1 : -1;
					}
				}
			}else {
				String result = request.getParameter("reponse");
				if (result != null) {
					int selectedRespId = Integer.parseInt(result);
					QuizReponse reponse = QuizDao.findResponseById(selectedRespId, cnx, false);
					score += reponse.isCorrect() ? 1 : -1;
				}
			}
			
			cnx.close();
			
			session.setAttribute("score", score);
			
			//Check s'il reste des questions on avance
			if(numQstEnCours < nbQuestions) {
				numQstEnCours++;
				session.setAttribute("numQstEnCours", numQstEnCours);
				displayQuestion(quizId, ConnectionDB.getConnection(urlConfig), request, response);
			}else {
				cible= "/quizResult.jsp";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		request.getRequestDispatcher(cible).forward(request, response);
	}
}
