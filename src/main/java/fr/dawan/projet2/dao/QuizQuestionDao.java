package fr.dawan.projet2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.dawan.projet2.beans.QuizQuestion;

public class QuizQuestionDao {

	public static QuizQuestion findQuestionByQuizId(int id, int order, Connection cnx, boolean closeCnx)
			throws SQLException {
		QuizQuestion question = null;
		String sql = "Select id, enonce, multipleChoice, ordre FROM t_question WHERE quizId = ? and ordre=?";
		PreparedStatement ps = cnx.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setInt(2, order);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int idQuestion = rs.getInt("id");
			question = new QuizQuestion(idQuestion, rs.getBoolean("multipleChoice"));
			question.setOrdre(rs.getInt("ordre"));
			question.setEnonce(rs.getString("enonce"));
			question.setReponses(QuizReponseDao.getResponses(idQuestion, cnx, false));
		}

		if (closeCnx)
			cnx.close();

		return question;
	}

	public static QuizQuestion findQuestionById(int questionId, Connection cnx, boolean closeCnx) throws SQLException {
		QuizQuestion question = null;
		String sql = "Select id, enonce, multipleChoice, ordre FROM t_question WHERE id = ?";
		PreparedStatement ps = cnx.prepareStatement(sql);
		ps.setInt(1, questionId);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			question = new QuizQuestion(questionId, rs.getBoolean("multipleChoice"));
			question.setOrdre(rs.getInt("ordre"));
			question.setEnonce(rs.getString("enonce"));
		}

		if (closeCnx)
			cnx.close();

		return question;
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
}
