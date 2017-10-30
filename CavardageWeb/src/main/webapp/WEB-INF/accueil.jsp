<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 03/10/17
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="homePage/js/date.js"></jsp:include>
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
        <jsp:include page="homePage/recherche/rechercheTrajet.jsp"></jsp:include>
    </form>
    <ul class="list-group">
        <jsp:include page="homePage/recherche/resultatsRecherche.jsp"></jsp:include>
    </ul>
</div>
<hr>

<h2>10 derniers ajouts:</h2>
<div>
    <form action="ControleurAnonyme" method="post">
        <ul class="list-group">
            <c:forEach items="${listeDernierTrajet}" var="trajet">
                <li class="list-group-item">
                    <table class="table table-bordered">
                        <tr>
                            <td>${trajet.villeDepart}(${trajet.departementDepart})</td>
                            <td>${trajet.villeArrivee}(${trajet.departementArrivee})</td>
                            <td>${trajet.date}</td>
                            <td>${trajet.heure}</td>
                        </tr>
                    </table>
                </li>
            </c:forEach>
        </ul>
    </form>
</div>


</body>
</html>

