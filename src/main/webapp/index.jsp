<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<p>Nombre de visiteurs : ${applicationScope.nbVisitors}</p>
	
	<p>
		Scriplet : <%
			// scriplets : code java
			String s  ="Mes d�buts en JEE";
			out.print(s);
		%><br />
		
		<%-- commentaire --%>
		Utilisation d'expression : <%= s %><br />	
	
		<%= s2 %>
		<%! String s2 = new String("Bonjour � tous !"); %><br/>
		<!-- %= est �valu� apr�s %! -->
		
		<%-- On peut utiliser des TagLibs 
		(biblioth�ques de balises XML qui g�n�rent du code java/html/css/js)
		--%>
		<jsp:useBean id="dateMaintenant" class="java.util.Date" />
	 	Date non format�e : <%=dateMaintenant %><br/>
	 	Date format�e : <fmt:formatDate value="${dateMaintenant}" /><br/>
	 	
	 	<jsp:useBean id="contact1" class="fr.dawan.projet2.beans.Contact" />
	 	<jsp:setProperty name="contact1" property="email" value="clorent@dawan.fr" />
	 	<jsp:getProperty name="contact1" property="email" />
	 	
	</p>
	<a href="params-servlet?action=list&num=3&num=5">R�cup�ration de param�tres</a><br />
	<a href="download">T�l�charger</a><br />	
	<a href="contact.jsp">Contact</a><br />
	
	<!-- Exo : formulaire authentification -->
	<a href="admin?action=login">CRUD [Create, Read, Update, Delete] complet (gestion)</a><br />
	<a href="order">Effectuer des achats</a><br />	
	<a href="cookies.jsp">Afficher les cookies</a><br />
	<a href="products?page=1&max=3">Afficher les produits avec pagination</a><br />	
	<a href="secured/secured.jsp">Acc�s � une page s�curis�e</a><br />
	<a href="objetsImplicites.jsp?nom=testNom&adr=adresse1&adr=adresse2">Exemples d'appels objets implicites<a /> <br />	
	<a href="quiz?action=start&quizId=1">Quiz</a><br />	
