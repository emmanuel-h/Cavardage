<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 01/11/17
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <form class="form-horizontal" method="post" action="ControleurUtilisateur">
        <div class="form-group">
            <label class="control-label col-sm-3">Ville de départ : </label>
            <div class="col-sm-5">
                <input type="text" class="form-control" value="${trajet.villeDepart}(${trajet.departementDepart})" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">Ville d'arrivée : </label>
            <div class="col-sm-5">
                <input type="text" class="form-control" value="${trajet.villeArrivee}(${trajet.departementArrivee})" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">Date de départ : </label>
            <div class="col-sm-2">
                <input type="text" class="form-control" value="${trajet.date}" readonly>
            </div>
            <div class="col-sm-1">
                <label> à </label>
            </div>
            <div class="col-sm-2">
                <input type="text" class="form-control" value="${trajet.heure}" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">Véhicule : </label>
            <div class="col-sm-5">
                <input type="text" class="form-control" value="${trajet.vehicule}" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">Prix : </label>
            <div class="col-sm-5">
                <input type="text" class="form-control" value="${trajet.prix} €" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">Étapes : </label>
            <div class="col-sm-5">
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

    <c:if test="${(fn:length(listePersonnes) > 1)}">
        <form class="form-horizontal" method="post" action="ControleurUtilisateur">
            <div class="form-group">
                <label class="control-label col-sm-3" for="personneNotee">Personne à noter : </label>
                <div class="col-sm-5">
                    <select class="form-control" name="loginPersonneAppreciation" id="personneNotee">
                        <c:forEach items="${listePersonnes}" var="personne">
                            <c:if test="${personne.login != pageContext.request.remoteUser}">
                                <option value="${personne.login}">${personne.login}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="note">Note : </label>
                <div class="col-sm-5">
                    <!-- <input type="text" name="note" id="note" autocomplete="off" placeholder="Note entre 0 et 5" pattern="[0-5]{1}|[0-4]{1}?\.?[0-9]{1}|5?\.0" required> -->
                    <input type="text" name="note" id="note" autocomplete="off" placeholder="Note entre 0 et 5" pattern="[0-5]{1}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="commentaire">Commentaire : </label>
                <div class="col-sm-5">
                    <textarea class="form-control" id="commentaire" name="commentaire" autocomplete="off"></textarea>
                </div>
            </div>
            <div class="form-group">
                <input type="hidden" name="idTrajet" value="${trajet.id}"/>
                <div class="col-sm-3">
                    <button type="submit" class="btn btn-primary pull-right" name="afaire" value="noter">Noter</button>
                </div>
            </div>
        </form>
    </c:if>

</div>

<!--
<%--
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
<c:if test="${(fn:length(listePersonnes) > 1)}">
<form method="post" action="ControleurUtilisateur">
    <select name = "loginPersonneAppreciation">
        <c:forEach items="${listePersonnes}" var="personne">
            <c:if test="${personne.login != pageContext.request.remoteUser}">
                <option value="${personne.login}">${personne.nom}</option>
            </c:if>
        </c:forEach>
    </select>
    <label>Note : </label><input type="text" name="note" autocomplete="off" pattern="[0-5]{1}" required/><br>
    <label>Commentaire : </label><textarea name="commentaire" autocomplete="off"></textarea>
    <input type="hidden" name="idTrajet" value="${trajet.id}"/>
    <button type="submit" name="afaire" value="noter">Noter</button>
</form>
</c:if>

--%>
-->