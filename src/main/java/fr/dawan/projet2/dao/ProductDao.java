package fr.dawan.projet2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.dawan.projet2.beans.Product;
import fr.dawan.projet2.controllers.MyLogger;

public class ProductDao {
	private static List<Product> productList;
	public static int nb = 0;
	
	static {
		productList = new ArrayList<>();
		try {
			productList.add(new Product(1, "Table", 99.99));
			nb++;
			productList.add(new Product(2, "Chaise", 23.5));
			nb++;
			productList.add(new Product(3, "Stylo", 0.97));
			nb++;
		} catch (Exception e) {
			MyLogger.myRootLogger.error("Erreur ajout produit" , e);
		}
	}
	
	public static List<Product> findAll(){
		return productList;
	}
	
	public static List<Product> findAll(Connection cnx, boolean closeCnx) throws SQLException{
		List<Product> productList = new ArrayList<>();
		String sql = "Select id, description, price FROM t_products";
		PreparedStatement ps = cnx.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			productList.add(new Product(rs.getInt("id"), rs.getString("description"), rs.getDouble("price")));
		}
		
		if (closeCnx) cnx.close();
		
		return productList;
	}
	
	public static List<Product> findAll(int start, int nb, Connection cnx, boolean closeCnx) throws SQLException{
		List<Product> productList = new ArrayList<>();
		String sql = "Select id, description, price FROM t_products LIMIT ?,?";
		PreparedStatement ps = cnx.prepareStatement(sql);
		ps.setInt(1, start);
		ps.setInt(2, nb);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			productList.add(new Product(rs.getInt("id"), rs.getString("description"), rs.getDouble("price")));
		}
		
		if (closeCnx) cnx.close();
		
		return productList;
	}
	
	public static long nbProducts(Connection cnx, boolean closeCnx) throws SQLException{
		long nb = 0;
		String sql = "Select count(id) FROM t_products";
		PreparedStatement ps = cnx.prepareStatement(sql);
		
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			nb = rs.getLong(1);
		}
		 
		if (closeCnx) cnx.close();
		
		return nb;
	}
	
	public static Product find(int id) {
		for (Product c : productList) {
			if(c.getId() == id) {
				return c;
			}
		}
		
		return null;
	}
}
