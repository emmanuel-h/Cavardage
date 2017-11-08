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
    <form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-3">Ville de départ : </label>
        <div class="col-sm-9">
            <input type="text" class="form-control" value="${histo.villeDepart}" readonly>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Ville d'arrivée : </label>
        <div class="col-sm-9">
            <input type="text" class="form-control" value="${histo.villeArrivee}" readonly>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Date de départ : </label>
        <div class="col-sm-4">
            <input type="text" class="form-control" value="${histo.dateDepart}" readonly>
        </div>
        <div class="col-sm-1">
            <label> à </label>
        </div>
        <div class="col-sm-4">
            <input type="text" class="form-control" value="${trajet.heure}" readonly>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Modèle de véhicule : </label>
        <div class="col-sm-9">
            <input type="text" class="form-control" value="${trajet.vehicule}" readonly>
        </div>
    </div>
    <div class="form-group">
        <c:choose>
            <c:when test="${histo.role == 'conducteur'}">
                <label class="control-label col-sm-3">Nom du véhicule : </label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" value="${histo.nomVehicule}" readonly>
                </div>
            </c:when>
            <c:otherwise>
                <label class="control-label col-sm-3">Nombre de places réservées : </label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" value="${histo.nbReservation}" readonly>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Prix du trajet : </label>
        <div class="col-sm-9">
            <input type="text" class="form-control" value="${trajet.prix} €" readonly>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Étapes : </label>
        <div class="col-sm-9">
            <ul class="list-group">
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
    </div>
    </form>
</div>
