<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <datalist id="listeVille">
        <c:forEach items="${listeVilles}" var="ville">
            <option value="${ville.nomVille}(${ville.departement})" />
        </c:forEach>
    </datalist>
    <datalist id="listeVehicule">
        <c:forEach items="${listeVehicules}" var="vehicule">
            <option value="${vehicule.nomVehicule}" />
        </c:forEach>
    </datalist>

<c:if test="${not empty message}">
<div class="alert alert-info">${message}</div>
</c:if>
<c:if test="${not empty messageErreur}">
    <div class="alert alert-danger">${messageErreur}</div>
</c:if>

    <form action="ControleurUtilisateur" method="post" >
        <legend>Proposer un trajet</legend>
        <div>
        <div class="col-lg-6">
            <div class="form-group">
                <label for="villeDepart">Ville de départ : </label>
                <input class="form-control" type="text" list="listeVille" id="villeDepart" name="villeDepart" value="${villeDepart}" readonly>
            </div>
            <div class="form-group">
                <label for="villeArrivee">Ville d'arrivée : </label>
                <input class="form-control" type="text" list="listeVille" id="villeArrivee" name="villeArrivee" value="${villeArrivee}" onblur="verifierVille()" readonly>
            </div>
            <div class="form-group">
                <label for="prixMoyen">Prix moyen pour ce trajet : </label>
                <c:choose>
                    <c:when test="${prixMoyen == -1}">
                        <label>Il n'existe pas de moyenne de prix pour ce trajet</label>
                    </c:when>
                    <c:otherwise>
                        <input class="form-control" type="text" id="prixMoyen" name="prixMoyen" value="${prixMoyen} €" readonly>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group">
                <label for="date">Le : </label>
                <input class="form-control" type="text" id="date" name="date" required placeholder="dd/mm/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" autocomplete="off">
            </div>
            <div class="form-group">
                <label for="heure">À : </label>
                <input style="width: 50px" class="form-control" type="text" id="heure" name="heure" placeholder="hh" autocomplete="off" required pattern="[0-9]{2}"> :
                <input style="width: 50px" class="form-control" type="text" id="minute" name="minute" placeholder="mm" autocomplete="off" required pattern="[0-9]{2}">
            </div>
            <div class="form-group">
                <label for="vehicule">Choix du véhicule : </label>
                <input class="form-control" type="text" id="vehicule" list="listeVehicule" name="vehicule" autocomplete="off" required>
            </div>
            <div class="form-group">
                <label for="prixVoyage">Prix du voyage : </label>
                <input class="form-control" placeholder="prix en €" type="text" id="prixVoyage" autocomplete="off" pattern="[0-9]{1,3}" name="prixVoyage" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" name="afaire" value="enregistrerTrajet">Créer</button>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label for="inputEtape">Ville étape : </label>
                <input class="form-control" type="text" list="listeVille" id="inputEtape" autocomplete="off">
            </div>
            <div class="form-group">
                <label for="inputPrix">Prix : </label>
                <input class="form-control" placeholder="prix en €" type="text" id="inputPrix" name="prix" pattern="[0-9]{1,3}" autocomplete="off">
            </div>
            <div class="form-group">
                <input type="button" name="afaire" class="btn btn-primary" value="Ajouter étape" onclick="ajouterEtape()" />
            </div>
            <div id="etapesDiv">
                <ul id="ulEtapes">

                </ul>
            </div>
        </div>
        </div>
    </form>

<jsp:include page="js/ajouterEtape.js"></jsp:include>