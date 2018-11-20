<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expression Language</title>
</head>
<body>

<!-- 

 accéder à l'attribut nom d'un objet personne situé dans la session avec Java
<%-- <%= session.getAttribute("personne").getNom() %> --%>

Exemple : accéder à l'attribut nom d'un objet personne situé dans la session avec EL
          ${sessionScope.personne.nom}



EL possède par défaut les variables suivantes :

PageScope        : variable contenue dans la portée de la page (PageContext)
RequestScope     : variable contenue dans la portée de la requête (HttpServletRequest)
SessionScope     : variable contenue dans la portée de la session (HttpSession)
ApplicationScope : variable contenue dans la portée de l'application (ServletContext)
Param            : paramètre de la requête http
ParamValues      : paramètres de la requête sous la forme d'une collection
Header           : en tête de la requête
HeaderValues     : en têtes de la requête sous la forme d'une collection
InitParam        : paramètre d'initialisation
Cookie           : cookie
PageContext      : objet PageContext de la page

 -->


<UL>
<LI><B>\${pageContext.response.contentType}:</B>
${pageContext.response.contentType}</LI>
<LI><B>\${param["nom"]}: </B>${param["nom"]}</LI>
<LI><B>\${param.nom}: </B>${param.nom}</LI>
<LI><B>\${param.adr}: </B>${param.adr}</LI>
<LI><B>\${paramValues.adr[0]}:</B>${paramValues.adr[0]}</LI>
<LI><B>\${paramValues.adr[1]}:</B>${paramValues.adr[1]}</LI>
<LI><B>\${header["user-agent"]}:</B>${header["user-agent"]}</LI>
<LI><b>\${cookie["JSESSIONID"].value}:</b> ${cookie["JSESSIONID"].value}</LI>
<LI><b>\${sessionScope["userName"]}:</b> ${sessionScope["userName"]}</LI>
<LI><b>\${sessionScope.userName}:</b> ${sessionScope.userName}</LI>
</UL>
</body>
</html>