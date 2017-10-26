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
    <title>Véhicules</title>
</head>
<body>
<form action="ControleurUtilisateur" method="post">
    Nom véhicule :<br>
    <input type="text" name="nomVehicule"><br>
    Modèle :<br>
    <input type="text" name="modeleVehicule"><br>
    <select name="gabaritVehicule">
        <c:forEach items="${listeGabarits}" var="gabarit">
            <option value="gabarit.idGabarit"> ${gabarit.type} </option>
        </c:forEach>
    </select><br>
    Places :<br>
    <input type="text" name="nbPlaces"><br>
    <button type="submit" name="afaire" value="enregistrerVehicule">Ajouter</button>
</form>
<br>
<br>

</form>