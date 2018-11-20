<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Formation Java EE : Servlets / JSP </h1>
<c:choose>
	<c:when test="${param.p1 == 1}">
		Header 1
	</c:when>
	<c:otherwise>
		Header other than 1
	</c:otherwise>
</c:choose>

