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
        <h2>Recherche d'un trajet:</h2>
        <label>Ville de départ:</label>
        <datalist id="listeVille">
        <c:forEach items="${listeVilles}" var="ville">
            <option value="${ville.nomVille}(${ville.departement})"/>
        </c:forEach>
        </datalist>
        <input type="text" list="listeVille" class="form-control" name="nomVilleDepart" required>
        <label>Ville d'arrivée:</label>
        <input type="text" list="listeVille" class="form-control" name="nomVilleArrivee" required>
        <label>Date:</label>
        <input type="date" name="date" required/>
        <label>Prix: (Optionnel)</label>
        <input type="text" name="prix"/>
        <button type="submit" name="afaire" value="rechercherTrajet">Rechercher</button>
    </form>
    <c:if test="${!empty villeDepart && !empty villeArrivee && !empty date}">
        <label>Résultat de la recherche: ${villeDepart} à destination de ${villeArrivee}, le ${date} <c:if test="${!empty prix}">pour un prix inférieur à ${prix}€</c:if>  </label>
    </c:if>
    <form action="ControleurAnonyme" method="post">
        <ul class="list-group">
            <c:forEach items="${listeTrajetRecherche}" var="trajet">
                <li class="list-group-item">
                    <table class="table table-bordered">
                        <tr>
                            <td>${trajet.villeDepart}(${trajet.departementDepart})</td>
                            <td>${trajet.villeArrive}(${trajet.departementArrivee})</td>
                            <td>${trajet.date}</td>
                            <td>${trajet.heure}</td>
                        </tr>
                    </table>
                </li>
            </c:forEach>
        </ul>
    </form>
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
                            <td>${trajet.villeArrive}(${trajet.departementArrivee})</td>
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

