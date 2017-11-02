<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="messageErreurDiv" class="alert alert-danger" hidden><label id="messageErreur"></label></div>

<div>
    <form action="ControleurUtilisateur" method="post" name="myForm" onsubmit="return verifForm(this)">
    <label>Ville de départ:</label> <label>${trajet.villeDepart}(${trajet.departementDepart})</label><br>
    <label>Ville d'arrivée:</label> <label>${trajet.villeArrivee}(${trajet.departementArrivee})</label><br>
    <label>Date:</label> <label>${trajet.date} ${trajet.heure}</label><br>
    <label>Prix:</label> <label>${trajet.prix}€</label><br>
    <label>Véhicule:</label> <label>${trajet.vehicule}</label><br>
    <label>Nombre de places disponibles:</label> <input name="placesRestantes" value="${trajet.nbPlaces}" readonly/><br>
        <label id="label_etape" hidden>Etape choisie:</label><input type="hidden" name="afficherEtape" readonly  /><input type="hidden" value="Supprimer" name="supprimerEtape" onclick="supprimerChampsEtape()"/> <br>

        <c:if test="${empty reservation}">
        <label>Nombre de places réservées:</label> <input type="text" name="nbPlacesReservees" required/>
        <input type="hidden" name="etapeArrivee" />
        <input type="hidden" name="villeArrivee" value="${trajet.villeArrivee}_${trajet.departementArrivee}"/>
        <input type="hidden" name="idTrajet" value="${trajet.id}" />
        <button type="submit" value="reserverTrajet" name="afaire">Réserver le trajet</button>
        </c:if>
    </form>
    <label>Etapes:</label>
    <li class="list-group-item">
        <table class="table table-bordered">
            <tr>
                <th>Nom de la ville étape</th>
                <th>Prix</th>
            </tr>
            <c:forEach items="${trajet.etapes}" var="etape">
                <tr>
                    <td>${etape.nomVilleArrivee}(${etape.departementArrivee})</td>
                    <td>${etape.prix}€</td>
                    <c:if test="${empty reservation}">
                    <td><input type="button" value="Descendre à cette étape" name="descenteEtape" onclick="saveAttribute('${etape.nomVilleArrivee}_${etape.departementArrivee}','${etape.nomVilleArrivee}')"/></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </li>

    <c:if test="${!empty reservation}">
        <h3>Vos informations de réservation</h3>
        <div>
            <label>Nombre de places réservées : </label>
            <input type="text" value="${reservation.nbPlaces}" readonly />
        </div>
        <div>
            <label>Ville d'arrivée : </label>
            <input type="text" value="${reservation.nomVilleArrivee}(${reservation.departementArrivee})" readonly />
        </div>
        <div>
            <label>État de la demande : </label>
            <input type="text" value="${reservation.statut}" readonly />
        </div>
        <div>
            <form method="post" action="ControleurUtilisateur">
                <input type="hidden" name="idReservation" value="${reservation.idReservation}"/>
                <button type="submit" name="afaire" value="supprimerReservation">Supprimer la réservation</button>
            </form>
        </div>


    </c:if>
</div>
<jsp:include page="js/detailsTrajet.js"/>