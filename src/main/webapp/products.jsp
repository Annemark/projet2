<%@page isErrorPage="false" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
	<title>Liste des produits</title>
</head>
	<h1>Liste des produits</h1>
	
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Date de création</th>
			<th>Produit</th>
			<th>Prix</th>
			<c:forEach var="p" items="${listeP}" varStatus="st">
				<tr>
					<td>${p.id}</td>
					<td><fmt:formatDate value="${p.creationDate}" pattern="dd/MM/yyyy" /></td>
					<td>${fn:toUpperCase(p.description)}</td>
					<td><fmt:formatNumber value="${p.price}" pattern="##.##" /> &euro;</td>			
				</tr>
			</c:forEach>
		</tr>
	</table>
	
	<c:if test="${param.page>1}">
		<a href="products?page=${param.page-1}&max=${param.max}">Précédent</a>
	</c:if>

	${param.page}

	<c:if test="${suivExist}">
		<a href="products?page=${param.page+1}&max=${param.max}">Suivant</a>
	</c:if>
	
