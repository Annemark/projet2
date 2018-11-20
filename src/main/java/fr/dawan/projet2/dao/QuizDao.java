package fr.dawan.projet2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.dawan.projet2.beans.Quiz;
import fr.dawan.projet2.beans.QuizReponse;

public class QuizDao {

	public static Quiz findQuizById(int id, Connection cnx, boolean closeCnx) throws SQLException {
		Quiz quiz = null;
		String sql = "Select id, title FROM t_quiz";
		PreparedStatement ps = cnx.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			quiz = new Quiz();
			quiz.setId(id);
			quiz.setTitle(rs.getString("title"));
		}

		if (closeCnx)
			cnx.close();

		return quiz;
	}

	public static int nbQuestions(int quizId, Connection cnx, boolean closeCnx) throws Exception {

		String sql = "Select COUNT(id) FROM t_question WHERE quizId = ?";
		PreparedStatement ps = cnx.prepareStatement(sql);
		ps.setInt(1, quizId);

		ResultSet rs = ps.executeQuery();
		int nb = 0;
		if (rs.next()) {
			nb = rs.getInt(1);
		}

		if (closeCnx)
			cnx.close();

		return nb;
	}

	public static QuizReponse findResponseById(int responseId, Connection cnx, boolean closeCnx) throws SQLException {
		QuizReponse reponse = null;
		String sql = "Select texte, correct, questionId FROM t_reponse WHERE id = ?";
		PreparedStatement ps = cnx.prepareStatement(sql);
		ps.setInt(1, responseId);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			reponse = new QuizReponse();
			reponse.setId(responseId);
			reponse.setCorrect(rs.getBoolean("correct"));
			reponse.setTexte(rs.getString("texte"));
			reponse.setQuestionId(rs.getInt("questionId"));
		}

		if (closeCnx)
			cnx.close();

		return reponse;
	}

}
