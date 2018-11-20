<%@page isErrorPage="false" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	<h1>Choix du produit</h1>
	<form method="post" action="order">
		<label for="product">Choisir un produit</label>
		<select id="product" name="product">
			<c:forEach var="p" items="${listeP}">
				<option value="${p.id}" >${p.description}<option>
			</c:forEach>
		</select>
		<label for="qte">Quantité</label>
		<input type="number" name="qte" id="qte" />
		<br/>
		<input type="submit" value="Ajouter au panier" />
	</form>
	
	<p>Nombre d'articles dans le panier : ${nbProducts}<p>	
	<a href="basket">Voir le panier</a>
