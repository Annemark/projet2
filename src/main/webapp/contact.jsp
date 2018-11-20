
	<h1>Contact</h1>
	<form method="post" action="contact">
		<label for="exp">Expéditeur</label>
		<input type="text" name="expediteur" id="exp" />
		<br/>
		<label for="mailExp">Email</label>
		<input type="text" name="mailExp" id="mailExp" />
		<br/>
		<label for="sujet">Sujet</label>
		<select name="sujet" id="sujet">
			<option value="qst">Question</option>
			<option value="recl">Réclamation</option>
		</select>
		<br/>
		<label for="msg">Sujet</label>
		<textarea rows="4" cols="30" id="msg" name="msg"></textarea>
		<br/>
		<input type="submit" value="Envoyer" />
	</form>
	
	<% 
		/* object obj = request.getAttribute("resultMsg");
				if (obj != null) out.print((String)obj)); */
				
	%>
	<p>${resultMsg}	<p>	
	<%-- <p>${param.nomVar}	<p>	
	<p>${nomVar}	<p>	
	<p>${requestScope.nomVar}	<p>	-> équivalent à ${nomVar}
	<p>${sessionScope.nomVar}	<p>	
	<p>${applicationScope.nomVar}	<p>	 --%>
