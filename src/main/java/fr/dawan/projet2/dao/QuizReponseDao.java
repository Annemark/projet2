package fr.dawan.projet2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.dawan.projet2.beans.QuizReponse;

public class QuizReponseDao {

	public static ArrayList<QuizReponse> getResponses(int id, Connection cnx, boolean closeCnx) throws SQLException {
		ArrayList<QuizReponse> reponses = new ArrayList<QuizReponse>();
		String sql = "Select id, texte, correct FROM t_reponse WHERE questionId = ?";
		PreparedStatement ps = cnx.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			QuizReponse reponse = new QuizReponse();
			reponse.setId(rs.getInt("id"));
			reponse.setCorrect(rs.getBoolean("correct"));
			reponse.setTexte(rs.getString("texte"));

			reponses.add(reponse);
		}

		if (closeCnx)
			cnx.close();

		return reponses;
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
