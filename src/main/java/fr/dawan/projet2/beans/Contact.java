package fr.dawan.projet2.beans;

import javax.mail.internet.InternetAddress;

public class Contact {
	private int id;
	private String name;
	private String email;
	private String password;
	
	public Contact() {
		super();	
	}
	
	public Contact(int id, String name, String email, String password) throws Exception {
		setId(id);
		setName(name);
		setEmail(email);
		setPassword(password);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) throws Exception {
		if (name != null && name.length() >= 2)
			this.name = name;
		else throw new Exception("Nom trop court");
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws Exception {
//		String regex = "";
//		if(Pattern.matches(regex, email)) {
//			this.email = email;
//		}
//		else throw new Exception("Email invalide");
		
		try {
			InternetAddress adr = new InternetAddress(email);
			adr.validate();
			this.email = email;
		} catch (Exception e) {
			throw new Exception("Email invalide");
		}
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) throws Exception {
		if (password != null && password.length() >= 6)
			this.password = password;
		else throw new Exception("Password trop court");
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
		Contact other = (Contact) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
