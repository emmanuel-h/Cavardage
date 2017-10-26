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
        <input type="password" name="mdp"/>
        <button type="submit" name="afaire" value="connexion">Connexion</button>
        <button type="submit" name="afaire" value="inscription">Inscription</button>

    </form>
</div>
<div>
    <form action="ControleurAnonyme" method="post">
        <h2>Recherche d'un trajet:</h2>
        <label>Ville de départ:</label>
        <c:forEach items="${listeVilles}" var="ville">
            <option value="${ville}"/>
        </c:forEach>
        <label>Ville d'arrivée:</label>
        <c:forEach items="${listeVilles}" var="ville">
            <option value="${ville}"/>
        </c:forEach>
        <label>Date:</label>
        <input type="date" name="date"/>
        <button type="submit" name="afaire" value="rechercherTrajet">Rechercher</button>
    </form>

</div>

<div>
    <form action="ControleurAnonyme" method="post">

    </form>
</div>


</datalist>

</body>
</html>

