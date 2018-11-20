<%@page isErrorPage="false" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	<h1>Afficher les cookies</h1>
	
	<%
	Cookie[] ckList = request.getCookies();
	out.print("nb Cookies : " + ckList.length + "<br />");
	
	
	//${nomVar} => request.getAttribute("cookies");
	%>
	
	<c:forEach var="ck" items="<%=ckList%>" varStatus="st">
		<c:out value="${ck.name}" /> = <c:out value="${ck.value}" />
		<c:if test="${not st.last}">
			<br />
		</c:if>
		
	</c:forEach>
