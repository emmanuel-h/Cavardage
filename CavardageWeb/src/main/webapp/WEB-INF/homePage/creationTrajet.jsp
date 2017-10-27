<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

${message}<br>

    <datalist id="listeVille">
        <c:forEach items="${listeVilles}" var="ville">
            <option value="${ville.nomVille} - ${ville.departement}" />
        </c:forEach>
    </datalist>
    <datalist id="listVehicule">
        <c:forEach items="${listeVehicules}" var="vehicule">
            <option value="${vehicule.nomVehicule}" />
        </c:forEach>
    </datalist>
    <form class="col-lg-6" action="ControleurUtilisateur" method="post">
        <!--<legend>Ajouter un trajet</legend>-->
        <div class="form-group">
            <label for="villeDepart">Ville de départ : </label>
            <input type="text" list="listeVille" id="villeDepart" name="villeDepart">
        </div>
        <div class="form-group">
            <label for="villeArrivee">Ville d'arrivée : </label>
            <input type="text" list="listeVille" id="villeArrivee" name="villeArrivee">
        </div>
        <div class="form-group">
            <label for="date">Le : </label>
            <input type="date" id="date" name="date">
        </div>
        <div class="form-group">
            <label for="heure">À : </label>
            <input type="text" id="heure" name="heure"> :
            <input type="text" id="minute" name="minute">
        </div>
        <div class="form-group">
            <label for="vehicule">Choix du véhicule : </label>
            <input type="text" id="vehicule" list="listVehicule" name="vehicule">
        </div>
        <div class="form-group">
            <label for="prixVoyage">Prix du voyage : </label>
            <input placeholder="prix en €" type="text" id="prixVoyage" name="prixVoyage">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary" name="afaire" value="enregistrerTrajet">Créer</button>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label for="inputEtape">Ville étape : </label>
                <input type="text" list="listeVille" id="inputEtape">
            </div>
            <div class="form-group">
                <label for="inputPrix">Prix : </label>
                <input placeholder="prix en €" type="text" id="inputPrix" name="prix">
            </div>
            <div class="form-group">
                <input type="button" name="afaire" class="btn btn-primary" value="ajouterEtape" onclick="ajouterEtape()">Ajouter étape</input>
            </div>
            <div id="etapesDiv">
                <ul id="ulEtapes">

                </ul>
            </div>
        </div>
    </form>


