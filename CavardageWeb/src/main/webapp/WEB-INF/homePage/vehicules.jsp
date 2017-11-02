<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="ControleurUtilisateur" method="post">
    <legend>Ajouter un véhicule</legend>
    <div class="form-group">
        <label for="nomVehicule">Nom du véhicule : </label>
        <input class="form-control" type="text" id="nomVehicule" name="nomVehicule" autocomplete="off" required>
    </div>
    <div class="form-group">
        <label for="modeleVehicule">Modèle du véhicule : </label>
        <input class="form-control" type="text" id="modeleVehicule" name="modeleVehicule" autocomplete="off" required>
    </div>
    <div class="form-group">
        <datalist id="listeGabarit">
            <c:forEach items="${listeGabarits}" var="gabarit">
                <option value="${gabarit.type}"></option>
            </c:forEach>
        </datalist>
        <label for="gabaritVehicule">Gabarit du véhicule : </label>
        <input class="form-control" type="text" list="listeGabarit" id="gabaritVehicule" name="gabaritVehicule" autocomplete="off" required>
    </div>
    <div class="form-group">
        <label for="nbPlaces">Nombre de places (hors conducteur) : </label>
        <input class="form-control" type="text" id="nbPlaces" name="nbPlaces" autocomplete="off" required>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="afaire" value="enregistrerVehicule">Ajouter</button>
    </div>
</form>

<hr>

<h3>Vos véhicules : </h3>
<c:choose>
    <c:when test="${!empty listeVehicules}">
        <ul class="list-group">
            <li class="list-group-item">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="col-lg-3">Nom</th>
                        <th class="col-lg-3">Modèle</th>
                        <th class="col-lg-3">Gabarit</th>
                        <th class="col-lg-3">Nombre de places</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listeVehicules}" var="vehicule">
                        <tr>
                            <td class="col-lg-3">${vehicule.nomVehicule}</td>
                            <td class="col-lg-3">${vehicule.modele}</td>
                            <td class="col-lg-3">${vehicule.nomGabarit}</td>
                            <td class="col-lg-3">${vehicule.nbPlaces}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </li>
            </form>
        </ul>
    </c:when>
    <c:otherwise>
        <h4>Vous n'avez pas encore de véhicule enregistré</h4>
    </c:otherwise>
</c:choose>


