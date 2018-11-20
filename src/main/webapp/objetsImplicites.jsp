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

 acc�der � l'attribut nom d'un objet personne situ� dans la session avec Java
<%-- <%= session.getAttribute("personne").getNom() %> --%>

Exemple : acc�der � l'attribut nom d'un objet personne situ� dans la session avec EL
          ${sessionScope.personne.nom}



EL poss�de par d�faut les variables suivantes :

PageScope        : variable contenue dans la port�e de la page (PageContext)
RequestScope     : variable contenue dans la port�e de la requ�te (HttpServletRequest)
SessionScope     : variable contenue dans la port�e de la session (HttpSession)
ApplicationScope : variable contenue dans la port�e de l'application (ServletContext)
Param            : param�tre de la requ�te http
ParamValues      : param�tres de la requ�te sous la forme d'une collection
Header           : en t�te de la requ�te
HeaderValues     : en t�tes de la requ�te sous la forme d'une collection
InitParam        : param�tre d'initialisation
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