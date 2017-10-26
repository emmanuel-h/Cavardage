<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="col-lg-4" method="post" action="ControleurUtilisateur">
    <legend>Ajouter un véhicule</legend>
    <div class="form-group">
        <label for="nomVehicule">Nom du véhicule : </label>
        <input type="text" id="nomVehicule" class="form-control" name="nomVehicule">
    </div>
    <div class="form-group">
        <label for="modelevehicule">Modèle du véhicule : </label>
        <input type="text" id="modelevehicule" class="form-control" name="modeleVehicule">
    </div>
    <div class="form-group">
        <label for="gabaritVehicule">Gabarit : </label>
        <datalist id="gabaritVehiculeList">
            <c:forEach items="${listeGabarits}" var="gabarit">
                <option value="${gabarit.type}"/>
            </c:forEach>
        </datalist>
        <input type="text" id="gabaritVehicule" list="gabaritVehiculeList" class="form-control" name="gabaritVehicule">
    </div>
    <div class="form-group">
        <label for="nbPlace">Nombre de places : </label>
        <input type="text" id="nbPlace" class="form-control" name="nbPlace">
    </div>
    <div class="form-group">
        <button class="btn btn-success" type="submit" name="afaire" value="ajouterVehicule">Ajouter</button>
    </div>
</form>

<form method="post" action="ControleurUtilisateur">

</form>
