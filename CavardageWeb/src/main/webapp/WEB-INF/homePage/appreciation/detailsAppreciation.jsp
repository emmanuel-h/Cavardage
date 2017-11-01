<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 01/11/17
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="ControleurUtilisateur" method="post" name="myForm">
    <label>Ville de départ:</label> <label>${trajet.villeDepart}(${trajet.departementDepart})</label><br>
    <label>Ville d'arrivée:</label> <label>${trajet.villeArrivee}(${trajet.departementArrivee})</label><br>
    <label>Date:</label> <label>${trajet.date} ${trajet.heure}</label><br>
    <label>Prix:</label> <label>${trajet.prix}€</label><br>
    <label>Véhicule:</label> <label>${trajet.vehicule}</label><br>
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
<form method="post" action="ControleurUtilisateur">
    <select name = "loginPersonneAppreciation">
        <c:forEach items="${listePersonnes}" var="personne">
            <c:if test="${personne.login != utilisateur}">
                <option value="${personne.login}">${personne.nom}</option>
            </c:if>
        </c:forEach>
    </select>
    <label>Note : </label><input type="text" name="note" required/><br>
    <label>Commentaire : </label><<textarea name="commentaire"></textarea>
    <input type="hidden" name="idTrajet" value="${trajet.id}"/>
    <button type="submit" name="afaire" value="noter">Noter</button>
</form>
