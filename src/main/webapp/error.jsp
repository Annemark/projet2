<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="fr.dawan.projet2.controllers.MyLogger" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="fr">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- <base href="http://localhost:8087/projet2/" />  -->
	 <base href="<%=request.getScheme() + "://"
		+ request.getServerName() + ":"
		+ request.getServerPort()
		+ request.getContextPath() + "/"
		%>" /> 
	<title>Error</title>
</head>
<body>
	<h1>Error</h1>
	
	<%
		ErrorData errors = pageContext.getErrorData();
		if (errors != null) {
			out.print("Code : " + errors.getStatusCode() + "<br>");
		}
		
		Exception ex = pageContext.getException();
		if (ex != null) {
			MyLogger.myRootLogger.error("Erreur non gérée" , ex);
			out.print(ex.getMessage() + "<br>");
		}		
	%>
</body>
</html>
