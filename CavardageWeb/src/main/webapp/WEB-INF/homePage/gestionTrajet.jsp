<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="messageErreurDiv" class="alert alert-danger" hidden><label id="messageErreur"></label></div>

<div>
    <form action="ControleurUtilisateur" method="post" name="myForm">
        <input type="hidden" name="idTrajet" value="${trajet.id}" />
        <label>Ville de départ:</label> <label>${trajet.villeDepart}(${trajet.departementDepart})</label><br>
        <label>Ville d'arrivée:</label> <label>${trajet.villeArrivee}(${trajet.departementArrivee})</label><br>
        <label>Date:</label> <label>${trajet.date} ${trajet.heure}</label><br>
        <label>Prix:</label> <label>${trajet.prix}€</label><br>
        <label>Véhicule:</label> <label>${trajet.nomVehicule}</label><br>
        <label>Nombre de places restantes:</label> <input type="text" name="nbPlacesRestantes" readonly value="${nbPlacesRestantes}"/>
        <button type="submit" value="supprimerTrajet" name="afaire">Supprimer le trajet</button>
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
                </tr>
            </c:forEach>
        </table>
    </li>
</div>
<div>
    <label>Liste des réservations acceptées:</label>
    <li class="list-group-item">
        <table class="table table-bordered">
            <tr>
                <th>Nom</th>
                <th>Nombre de places</th>
                <th>Ville d'arrivée</th>
            </tr>
            <c:forEach items="${reservationsAcceptees}" var="reservation">
                <tr>
                    <td>${reservation.nomUtilisateur}</td>
                    <td>${reservation.nbPlaces}</td>
                    <td>${reservation.nomVilleArrivee}(${reservation.departementArrivee})</td>
                </tr>
            </c:forEach>
        </table>
    </li>
</div>
<div>

    <label>Liste des réservations en attente:</label>
    <li class="list-group-item">
        <table class="table table-bordered">
            <tr>
                <th>Nom</th>
                <th>Nombre de places</th>
                <th>Ville d'arrivée</th>
            </tr>
            <c:forEach items="${reservationsAttente}" var="reservation">
                <form method="post" action="ControleurUtilisateur" onsubmit="return testReservation(this)">
                    <tr>
                        <td>${reservation.nomUtilisateur}</td>
                        <td>${reservation.nbPlaces}</td>
                        <td>${reservation.nomVilleArrivee}(${reservation.departementArrivee})</td>
                        <td>
                            <input type="hidden" name="nbPlacesReservation" value="${reservation.nbPlaces}">
                            <input type="hidden" name="idReservation" value="${reservation.idReservation}" />
                            <input type="hidden" name="idTrajet" value="${trajet.id}" />
                            <button type="submit" name="afaire" value="accepterReservation" onclick="modifQuelBouton()">V</button>
                            <button type="submit" name="afaire" value="refuserReservation">X</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            <form name="formQuelBouton">
            <input type="hidden" name="quelBouton" value="refuser">
            </form>
        </table>
    </li>
</div>

<script>
    function modifQuelBouton(){
        document.formQuelBouton.quelBouton.value = "accepter";
    }
    function testReservation(f) {
        var placesRestantes = document.myForm.nbPlacesRestantes.value;
        var quelBouton = document.formQuelBouton.quelBouton.value;
        var placesReservation = f.nbPlacesReservation.value;
        if(quelBouton != "accepter"){
            return true;
        }
        if(+placesRestantes >= +placesReservation) {
            return true;
        }
        document.getElementById("messageErreur").innerHTML = 'Vous ne pouvez pas accepter cette réservation';
        document.getElementById("messageErreurDiv").style.display = 'block';
        return false;
    }
</script>

