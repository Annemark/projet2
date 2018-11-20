
	<h1>Authentification</h1>
	<form method="post" action="admin">
		<label for="email">Login</label>
		<input type="text" name="email" id="email" value="toto@dawan.fr" />
		<br/>
		<label for="password">Email</label>
		<input type="password" name="password" id="password" />
		<br/>
		<input type="hidden" name="action" value="connect" />
		<input type="submit" value="Se connecter" />
	</form>
	
	<p>${msg}<p>