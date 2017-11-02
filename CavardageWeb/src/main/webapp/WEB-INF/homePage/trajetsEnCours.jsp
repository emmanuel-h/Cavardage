<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Vos trajets en cours</h3>
<c:choose>
    <c:when test="${empty listeTrajetsConducteur}">
        <h4>Vous ne proposez pas encore de trajet</h4>
    </c:when>
    <c:otherwise>
        <h4>Vos trajets en tant que conducteurs : </h4>
        <ul class="list-group">
            <li class="list-group-item">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="col-lg-3">Ville de départ</th>
                        <th class="col-lg-3">Ville d'arrivée</th>
                        <th class="col-lg-2">Date de départ</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listeTrajetsConducteur}" var="trajet">
                        <tr>
                            <td class="col-lg-3">${trajet.villeDepart}(${trajet.departementDepart})</td>
                            <td class="col-lg-3">${trajet.villeArrivee}(${trajet.departementArrivee})</td>
                            <td class="col-lg-3">${trajet.date}</td>
                            <td class="col-lg-1">
                                <form method="post" action="ControleurUtilisateur">
                                    <input type="hidden" name="idTrajet" value="${trajet.id}">
                                    <button class="btn btn-primary" type="submit" name="afaire" value="gererTrajet">Gérer</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </li>
        </ul>
    </c:otherwise>
</c:choose>
<hr>
<c:choose>
    <c:when test="${empty listeTrajetsPassager}">
        <h4>Vous n'avez pas encore réservé de trajet</h4>
    </c:when>
    <c:otherwise>
        <h4>Vos trajets en tant que passager : </h4>
        <ul class="list-group">
            <li class="list-group-item">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="col-lg-3">Ville de départ</th>
                        <th class="col-lg-3">Ville d'arrivée</th>
                        <th class="col-lg-3">Date de départ</th>
                        <th class="col-lg-2">Statut</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listeTrajetsPassager}" var="reservation">
                        <tr>
                            <td class="col-lg-3">${reservation.nomVilleDepart}(${reservation.departementDepart})</td>
                            <td class="col-lg-3">${reservation.nomVilleArrivee}(${reservation.departementArrivee})</td>
                            <td class="col-lg-3">${reservation.date}</td>
                            <td class="col-lg-2">${reservation.statut}</td>
                            <td class="col-lg-1">
                                <form method="post" action="ControleurUtilisateur">
                                    <input type="hidden" name="idTrajet" value="${reservation.idTrajet}">
                                    <input type="hidden" name="idReservation" value="${reservation.idReservation}">
                                    <button class="btn btn-primary" type="submit" name="afaire" value="detailsReservation">Détails</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </li>
        </ul>
    </c:otherwise>
</c:choose>
