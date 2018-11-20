
	<h1>Authentification Securisée</h1>
	<form method="post" action="j_security_check">
		<label for="email">Login</label>
		<input type="text" name="j_username" id="email" value="tutu" />
		<br/>
		<label for="password">Email</label>
		<input type="password" name="j_password" id="password" />
		<br/>
		<input type="hidden" name="action" value="connect" />
		<input type="submit" value="Se connecter" />
	</form>
	
	<p>${msg}<p>