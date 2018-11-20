package fr.dawan.projet2.beans;

import java.util.ArrayList;

public class QuizQuestion {
	
	private int id;
    private String enonce;
    private boolean multipleChoice;
    private int ordre;
    private int quizId;
    private ArrayList<QuizReponse> reponses; 
   
    public QuizQuestion(int id, boolean isMultipleChoice)
    {
        this.id = id;
        this.multipleChoice = isMultipleChoice;
        this.reponses = new ArrayList<>();
       
        
        //this.setId(id);
       // this.setMultipleChoice(isMultipleChoice);
        //this.setReponses(new ArrayList<>());
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	
	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public boolean isMultipleChoice() {
		return multipleChoice;
	}

	public void setMultipleChoice(boolean multipleChoice) {
		this.multipleChoice = multipleChoice;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public ArrayList<QuizReponse> getReponses() {
		return reponses;
	}

	public void setReponses(ArrayList<QuizReponse> reponses) {
		this.reponses = reponses;
	}

    
    
}
