<%@page isErrorPage="false" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
	String s = "TTT";
%>
<c:set var="s2" value="aaaa" />

	<h1>Mon panier</h1>
	<table border="1">
		<tr>
			<th>N°</th>
			<th>Produit</th>
			<th>Qté</th>
			<th>PU</th>
			<th>PT</th>
			<th>Action</th>
			<c:forEach var="bl" items="${listeBL}" varStatus="st">
				<tr>
					<td>${st.index+1}</td>
					<td>${bl.product.description}</td>
					<td>
						<a href="basket?action=changeqty&num=${st.index}&qte=${bl.quantity-1}">-</a>
						${bl.quantity}
						<a href="basket?action=changeqty&num=${st.index}&qte=${bl.quantity+1}">+</a>
					</td>
					<td><fmt:formatNumber value="${bl.product.price}" pattern="##.##" /> €</td>
					<td>${bl.total}</td>
					<td><a href="basket?action=remove&num=${st.index}">x</a></td>					
				</tr>
			</c:forEach>
		</tr>
	</table>
	<p>Total panier : ${totalBasket}</p>
