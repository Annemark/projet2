package fr.dawan.projet2.beans;

import java.util.Date;

public class Product {
	private int id;
	private String description;
	private double price;
	private Date creationDate;
	
	public Product() {
		super();
		creationDate = new Date();
	}
	
	public Product(int id, String description, double price) {
		setId(id);
		setDescription(description);
		setPrice(price);
		creationDate = new Date();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	// Source / Generate hashcode and equals / sélectionner Id
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
