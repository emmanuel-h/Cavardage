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
        <div class="panel panel-default">
            <div class="panel-body" style="background-color: #e6f7ff">
               <div class="form-group col-lg-4">
                <label>Ville de départ:</label> <label>${trajet.villeDepart}(${trajet.departementDepart})</label>
               </div>
                <div class="form-group col-lg-4">
                   <label>Ville d'arrivée:</label> <label>${trajet.villeArrivee}(${trajet.departementArrivee})</label>
                </div>
                <div class="form-group col-lg-4">
                    <label>Date:</label> <label>${trajet.date} ${trajet.heure}</label>
                </div>
                <div class="form-group col-lg-4">
                    <label>Prix:</label> <label>${trajet.prix}€</label>
                </div >
                <div class="form-group col-lg-4">
                    <label>Véhicule:</label> <label>${trajet.vehicule}</label>
                </div>
                <div class="form-group col-lg-3">
                    <label>Nombre de places disponibles:</label>
                </div>
                <input class="form-group col-lg-1" name="placesRestantes" value="${trajet.nbPlaces}" readonly/>
                <div class="form-group col-lg-4">
                    <label id="label_etape" hidden>Etape choisie:</label>
                    <input type="hidden" name="afficherEtape" readonly  />
                </div>
                <div class="form-group col-lg-4">
                    <input type="hidden" class="btn btn-danger" value="Supprimer" name="supprimerEtape" onclick="supprimerChampsEtape()"/>
                </div>
                <c:if test="${empty reservation}">
                    <div class="form-group col-lg-3">
                        <label>Nombre de places réservées:</label>
                    </div>
                    <input class="form-group col-lg-1" type="text" name="nbPlacesReservees" required/>
                    <input class="form-group col-lg-3" type="hidden" name="etapeArrivee" />
                    <input class="form-group col-lg-3" type="hidden" name="villeArrivee" value="${trajet.villeArrivee}_${trajet.departementArrivee}"/>
                    <input class="form-group col-lg-3" type="hidden" name="idTrajet" value="${trajet.id}" />
                    <div class="clearfix"/>
                    <div class="form-group col-lg-3">
                        <button type="submit" class="btn btn-default" value="reserverTrajet" name="afaire">Réserver le trajet</button>
                    </div>
                </c:if>
            </div>
        </div>
    </form>
    <label>Etapes:</label>
    <c:if test="${not empty trajet.etapes}">
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
                        <td><input type="button" class="btn btn-default" value="Descendre à cette étape" name="descenteEtape" onclick="saveAttribute('${etape.nomVilleArrivee}_${etape.departementArrivee}','${etape.nomVilleArrivee}')"/></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </li>
    </c:if>
    <c:if test="${empty trajet.etapes}">
        <div class="alert alert-warning" style="margin-top: 10px">
                Il n'y pas d'étape sur ce trajet
        </div>
    </c:if>
    <c:if test="${!empty reservation}">
        <h3>Vos informations de réservation</h3>
        <div class="panel panel-default">
            <div class="panel-body" style="background-color: #e6f7ff">
                <div class="form-group col-lg-4">
                    <label>Nombre de places réservées : </label>
                    <input type="text" value="${reservation.nbPlaces}" readonly />
                </div>
                <div class="form-group col-lg-4">
                    <label>Ville d'arrivée : </label>
                    <input type="text" value="${reservation.nomVilleArrivee}(${reservation.departementArrivee})" readonly />
                </div >
                <div class="form-group col-lg-4">
                    <label>État de la demande : </label>
                    <input type="text" value="${reservation.statut}" readonly />
                </div>
                <div class="form-group col-lg-4">
                    <form method="post" action="ControleurUtilisateur">
                        <input type="hidden" name="idReservation" value="${reservation.idReservation}"/>
                        <button type="submit" class="btn btn-danger" name="afaire" value="supprimerReservation">Supprimer la réservation</button>
                    </form>
                </div>
            </div>
        </div>
    </c:if>
</div>
<jsp:include page="js/detailsTrajet.js"/>