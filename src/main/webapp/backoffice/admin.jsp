<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="Error.jsp" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="fr.dawan.projet2.beans.Contact" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	<h1>Admin</h1>
	<p>Bonjour ${sessionScope.userName}</p>	<br />
	<a href="admin?action=disconnect">Se déconnecter</a><br/>
	Année de création du servlet : ${monAnnee} par ${authorName}
	
	<h2>Liste des contacts</h2>
	<table border="1">
		<%-- <%
			Object obj = request.getAttribute("listeC");
			if (obj != null){
				List<Contact> lc = (List<Contact>)obj;
				for (Contact c : lc){
					out.print("<tr><td>");
					out.print(c.getName());
					out.print("</td><td>");
					out.print(c.getEmail());
					out.print("</td></tr>");
				}
			}
		%> --%>
		<c:forEach var="c" items="${listeC}">
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th>Email</th>
				<th>Actions</th>
			</tr>
			<tr>
				<td>${c.id}</td>
				<td>${c.name}</td>
				<td>${c.email}</td>
				<td>
					<a href="admin?action=edit&id=${c.id}">Editer</a>
					<a href="admin?action=remove&id=${c.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<br />
	<fieldset>
		<legend>Ajout / Modif</legend>
		<form method="post" action="admin">
			<label for="name">Nom : </label>
			<input type="text" name="name" id="name" value="${updatedContact.name}" /><br/>
			<label for="email">Email : </label>
			<input type="text" name="email" id="email" value="${updatedContact.email}" /><br/>
			<label for="password">Mot de passe : </label>
			<input type="password" name="password" id="password" value="${updatedContact.password}" /><br/>
			
			<input type="hidden" id="id" value="${updatedContact.id}" name="id" /><br/>
			<input type="hidden" name="action" value="save" />
			<input type="submit" value="Sauvegarder" />
		</form>
	</fieldset>
	<br />
	
	<h3>Import / Export CSV</h3>
	<a href="admin?action=exportcsv">Export CSV</a>
	
	<form method="post" action="upload" enctype="multipart/form-data">
		<input type="file" name="file" size="80" /><br>
		<input type="submit" value="Upload du CSV" />
	</form>
	<p>${resultUpload}</p>
