package fr.dawan.projet2.beans;

import java.util.ArrayList;
import java.util.List;

public class Basket {
	private List<BasketLine> lines;
	
	public Basket() {
		lines = new ArrayList<BasketLine>();
	}
	
	public List<BasketLine> getLines() {
		return lines;
	}

	public void setLines(List<BasketLine> lines) {
		this.lines = lines;
	}	
	
	public double getTotal() {
		double total = 0;
		for (BasketLine basketLine : lines) {
			total += basketLine.getTotal();
		}
		
		return total;
	}
	
	public int getNbArticle() {
		int total = 0;
		for (BasketLine basketLine : lines) {
			total += basketLine.getQuantity();
		}
		
		return total;
	}
}
