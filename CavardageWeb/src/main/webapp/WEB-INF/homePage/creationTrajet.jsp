<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Création trajet</title>
</head>
<body>
<form action="ControleurUtilisateur" method="post">
    Ville de départ :<br>
    <input type="text" name="villeDepart">
    Ville d'arrivée :<br>
    <input type="text" name="villeArrivee">
    A partir de :<br>
    <input type="date" name="date">
    Choix du véhicule :<br>
    <select name="vehicule">
    <c:forEach items="${listeVehicules}" var="vehicule">
        <option value="vehicule.idVehicule"> ${vehicule.nom} </option>
    </c:forEach>
    </select>
    Ajouter une étape :<br>
    <input type="text" name="etape">
    <button type="submit" name="afaire" value=ajouterEtape">Ajouter étape</button>

    <button type="submit" name="afaire" value="enregistrerTrajet">Créer</button>
</form>
</body>
</html>
