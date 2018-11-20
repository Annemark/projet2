<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- <base href="http://localhost:8087/projet2/" />  -->
	 <base href="<%=request.getScheme() + "://"
		+ request.getServerName() + ":"
		+ request.getServerPort()
		+ request.getContextPath() + "/"
		%>" /> 
	
	<!--  balise fixe title avec une valeur modifiable/par défaut pour le titre -->
	<title>Hi : <decorator:title default="Formation Java EE : Servlets / JSP" /> </title>
		
 <decorator:head>
	<!-- région modifiable pour les css/js, ... -->
</decorator:head>

</head>
<body>

	<!-- <h1>Formation Java EE : Servlets / JSP</h1> -->
	<jsp:include page="header.jsp">
		<jsp:param value="1" name="p1" />
	</jsp:include>
	<decorator:body></decorator:body>
	<%@include file="footer.jsp" %>
</body>
</html>
