<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <form action="ControleurUtilisateur" method="post" name="myForm">
    <label>Ville de départ:</label> <label>${trajet.villeDepart}(${trajet.departementDepart})</label><br>
    <label>Ville d'arrivée:</label> <label>${trajet.villeArrivee}(${trajet.departementArrivee})</label><br>
    <label>Date:</label> <label>${trajet.date} ${trajet.heure}</label><br>
    <label>Prix:</label> <label>${trajet.prix}€</label><br>
    <label>Véhicule:</label> <label>${trajet.vehicule}</label><br>
    <label>Nombre de places disponibles:</label> <label>${trajet.nbPlaces}</label><br>
        <label id="label_etape" hidden>Etape choisie:</label><input type="hidden" name="afficherEtape" readonly  /><input type="hidden" value="Supprimer" name="supprimerEtape" onclick="supprimerChampsEtape()"/> <br>
    <label>Nombre de places réservées:</label> <input type="text" name="nbPlacesReservees" required/>
        <input type="hidden" name="etapeArrivee" />
        <input type="hidden" name="villeArrivee" value="${trajet.villeArrivee}_${trajet.departementArrivee}"/>
        <input type="hidden" name="idTrajet" value="${trajet.id}" />
     <button type="submit" value="reserverTrajet" name="afaire">Réserver le trajet</button>
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
                    <td><input type="button" value="Descendre à cette étape" name="descenteEtape" onclick="saveAttribute('${etape.nomVilleArrivee}_${etape.departementArrivee}','${etape.nomVilleArrivee}')"/></td>
                </tr>
            </c:forEach>
        </table>
    </li>


</div>
<script>
function saveAttribute(idEtape,nomEtape)
{
document.myForm.afficherEtape.value = nomEtape;
document.myForm.etapeArrivee.value = idEtape;
document.myForm.afficherEtape.type = "text";
document.getElementById("label_etape").style.display = 'block';
document.myForm.supprimerEtape.type = "button";
}

function supprimerChampsEtape() {
    document.myForm.supprimerEtape.type = "hidden";
    document.myForm.afficherEtape.type = "hidden";
    document.getElementById("label_etape").style.display = 'none';
    document.myForm.etapeArrivee.value = null;

}

</script>