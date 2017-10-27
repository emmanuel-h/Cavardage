<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="js/ajouterEtape.js"></jsp:include>
<dataliste id="listeVille">
    <c:forEach items="${listeVilles}" var="ville">
        <option value="${ville.nomVille}" />
    </c:forEach>
</dataliste>
<dataliste id="listeVehicule">
    <c:forEach items="${listeVehicules}" var="vehicule">
        <option value="${vehicule.nom}" />
    </c:forEach>
</dataliste>
<form action="ControleurUtilisateur" method="post">
    Ville de départ :<br>
    <input type="text" name="villeDepart">
    Ville d'arrivée :<br>
    <input type="text" name="villeArrivee">
    A partir de :<br>
    <input type="date" name="date">
    Choix du véhicule :<br>

    <input type="text" liste="listeVehicule" name="vehicule">
    <button type="submit" name="afaire" value="enregistrerTrajet">Créer</button>
</form>

Ajouter une étape :<br>
<input type="text" id="inputEtape" name="etape">
<button name="afaire" value="ajouterEtape" onclick="ajouterEtape()">Ajouter étape</button>
<div id="etapesDiv">
    <ul id="ulEtapes">

    </ul>
</div>

</body>
</html>
