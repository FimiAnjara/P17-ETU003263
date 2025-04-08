<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
</head>
<body>
    <div class="login-container">
        <h1>Connexion à votre compte</h1>
        <div>
            Pour voir le dashboard : <a href="TraitPrevision">Dasboard</a>
        </div>

        <%  if(request.getAttribute("error")!= null) { %>
        <div class="error-message" id="errorMessage">
            <%= request.getAttribute("error") %>
        </div>
        <% }%>
        
        <form action="login" method="post" id="loginForm">
            <div class="form-group">
                <label for="username">Nom d'utilisateur</label>
                <input type="text" id="username" name="username" required autofocus placeholder="admin">
            </div>
            
            <div class="form-group">
                <label for="password">Mot de passe</label>
                <input type="password" id="password" name="password" required placeholder="1234">
            </div>
            
            <button type="submit">Se connecter</button>
        </form>
    </div>
</body>
</html>