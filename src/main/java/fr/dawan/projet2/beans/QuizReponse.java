package fr.dawan.projet2.beans;

public class QuizReponse {

	private int id;
	private String texte;
	private  boolean correct;
	private int questionId;
	
	
	public QuizReponse() {
		
	}
	
	
	public QuizReponse(int id, String texte, boolean correct, int questionId) {
		super();
		this.id = id;
		this.texte = texte;
		this.correct = correct;
		this.questionId = questionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	
	
	
}
