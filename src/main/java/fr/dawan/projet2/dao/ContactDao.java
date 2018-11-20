package fr.dawan.projet2.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import fr.dawan.projet2.beans.Contact;
import fr.dawan.projet2.controllers.MyLogger;

public class ContactDao {
	private static List<Contact> contactList;
	public static int nb = 0;
	
	static {
		contactList = new ArrayList<>();
		try {
			contactList.add(new Contact(1, "Toto", "toto@dawan.fr", "tototo"));
			nb++;
			contactList.add(new Contact(2, "Titi", "titi@dawan.fr", "tititi"));
			nb++;
			contactList.add(new Contact(3, "Tata", "tata@dawan.fr", "tatata"));
			nb++;
		} catch (Exception e) {
			MyLogger.myRootLogger.error("Erreur" , e);
		}
	}
	
	public static List<Contact> findAll(){
		return contactList;
	}
	
	public static Contact find(String email){
		for (Contact c : contactList) {
			if(email != null && email.equalsIgnoreCase(c.getEmail())) {
				return c;
			}
		}
		
		return null;
	}
	
	public static int add(Contact c){
		c.setId(++nb);
		contactList.add(c);
		return 1;
	}
	
	public static Contact find(int id) {
		for (Contact c : contactList) {
			if(c.getId() == id) {
				return c;
			}
		}
		
		return null;
	}
	
	public static int remove(int id) {
		Contact cx = new Contact();
		cx.setId(id);
		int pos = contactList.indexOf(cx);
		if (pos != -1) {
			contactList.remove(pos);
			return 1;
		}
		
		return 0;
	}
	
	public static int update(Contact c) throws Exception {
		int pos = contactList.indexOf(c);
		if (pos != -1) {
			contactList.get(pos).setName(c.getName());
			contactList.get(pos).setEmail(c.getEmail());
			contactList.get(pos).setPassword(c.getPassword());
			return 1;
		}
		
		return 0;
	}
	
	public static List<Contact> importCsv(String filePath) throws NumberFormatException, Exception {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] datas = line.split(";");
			Contact c = new Contact(Integer.parseInt(datas[0]), datas[1], datas[2],datas[3]);
			ContactDao.add(c);
		}
		br.close();
		return ContactDao.findAll();
	}

}
