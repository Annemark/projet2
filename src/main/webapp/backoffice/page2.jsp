<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="fr.dawan.projet2.beans.Contact" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



	<h1>Admin 2</h1>
	<p>Bonjour ${sessionScope.userName}</p>	<br />
	<a href="admin?action=disconnect">Se déconnecter</a>
	
	