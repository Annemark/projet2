<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<h1>Quiz : ${quiz.title}</h1>
	
	<h3>${question.enonce}</h3>	
	
	<c:forEach var="element" items="{question.reponses}">
	</c:forEach>
	
	<form action="quiz" method="post">
		<c:choose>
			<c:when test="${question.multipleChoice}">
				<c:forEach  var="r" items="${reponses}">
					<input id="choice" type="checkbox" value="${r.id}" name="reponse" />
					<label for="choice">${r.texte}</label><br />
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach  var="r" items="${reponses}">
					<input id="choice" type="radio" value="${r.id}" name="reponse" />
					<label for="choice">${r.texte}</label><br />
				</c:forEach>
			</c:otherwise>
		</c:choose>
	
		<input type="hidden" name="questionId" value="${question.id}" />
		<input type="hidden" name="quizId" value="${quiz.id}" />
		<input type="hidden" name="action" value="suivante" />
		<!-- <input type="hidden" name="action" value="validateQuestion" /> -->
		<input type="submit" value="Suivante" />
	</form>
	
	
