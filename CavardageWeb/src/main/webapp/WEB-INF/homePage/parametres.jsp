<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Paramètres</title>
</head>
<body>
${message}<br>
<form action="ControleurUtilisateur" method="post">
    <label>Nouveau mot de passe</label>
    <input type="password" name="nouveauMdP1">
    <label>Retapez le mot de passe</label>
    <input type="password" name="nouveauMdP2">
    <button type="submit" name="afaire" value="changerMotDePasse">Changer mot de passe</button>
    <button type="submit" name="afaire" value="supprimerCompte">Supprimer compte</button>
</form>
</body>
</html>
