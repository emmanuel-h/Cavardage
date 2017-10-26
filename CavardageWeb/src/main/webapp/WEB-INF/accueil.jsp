<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 03/10/17
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cavardage ~ Accueil ~</title>
</head>
<body>

<h1 style="text-align: center">Cavardage</h1>

<hr>

<p>Bonjour ${utilisateur.nom}</p>

<div>
    <form method="post" action="ControleurAnonyme" >
        <label>Login:</label>
        <input type="text" name="login"/>
        <label>Mot de passe:</label>
        <input type="text" name="mdp"/>
        <button type="submit" name="afaire" value="connexion">Connexion</button>
        <button type="submit" name="afaire" value="inscription">Inscription</button>
    </form>
</div>




</body>
</html>

