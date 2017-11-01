<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 01/11/17
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <label>Ville de départ : </label> <label>${histo.villeDepart}</label> <br>
    <label>Ville d'arrivée : </label> <label>${histo.villeArrivee}</label> <br>
    <label>Date de départ : </label> <label>${histo.dateDepart}</label> à <label>${trajet.heure}</label> <br>
    <label>Modèle de véhicule : </label> <label>${trajet.vehicule}</label> <br>
    <c:choose>
        <c:when test="${histo.role == 'conducteur'}">
            <label>Nom du véhicule : </label> <label>${histo.nomVehicule}</label> <br>
        </c:when>
        <c:otherwise>
            <label>Nombre de places réservées : </label> <label>${histo.nbReservation}</label> <br>
        </c:otherwise>
    </c:choose>
    <label>Prix du trajet : </label> <label>${trajet.prix} €</label> <br>
    <label>Étapes : </label> <ul class="list-group">
            <c:choose>
                <c:when test="${empty trajet.etapes}">
                    <h4>Ce trajet ne comportait pas d'étapes</h4>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${trajet.etapes}" var="etape">
                        <li class="list-group-item">
                            <label>${etape.nomVilleArrivee}(${etape.departementArrivee})</label> - <label>${etape.prix} €</label>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
</div>
