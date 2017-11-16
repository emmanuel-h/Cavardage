<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3>Historique de vos trajets : </h3>
<c:choose>
    <c:when test="${empty listeHistorique}">
        <h4>Vous n'avez pas encore participé à un trajet</h4>
    </c:when>
    <c:otherwise>
        <ul class="list-group">
            <li class="list-group-item">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th class="col-lg-3">Ville de départ</th>
                            <th class="col-lg-3">Ville d'arrivée</th>
                            <th class="col-lg-2">Date de départ</th>
                            <th class="col-lg-2">Véhicule</th>
                            <th class="col-lg-1">Places réservées</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listeHistorique}" var="histo">
                            <c:if test="${histo.role == 'conducteur'}">
                                <tr class="warning">
                                    <td class="col-lg-3">${histo.villeDepart}</td>
                                    <td class="col-lg-3">${histo.villeArrivee}</td>
                                    <td class="col-lg-2">${histo.dateDepart}</td>
                                    <td class="col-lg-2">${histo.nomVehicule}</td>
                                    <td class="col-lg-1"> - </td>
                                    <td class="col-lg-1">
                                        <form method="post" action="ControleurUtilisateur">
                                            <input type="hidden" name="idTrajet" value="${histo.idTrajet}">
                                            <button class="btn btn-primary" type="submit" name="afaire" value="detailHistorique">Details</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${histo.role == 'passager'}">
                                <tr class="success">
                                    <td class="col-lg-3">${histo.villeDepart}</td>
                                    <td class="col-lg-3">${histo.villeArrivee}</td>
                                    <td class="col-lg-2">${histo.dateDepart}</td>
                                    <td class="col-lg-2"> - </td>
                                    <td class="col-lg-1">${histo.nbReservation}</td>
                                    <td class="col-lg-1">
                                        <form method="post" action="ControleurUtilisateur">
                                            <input type="hidden" name="idTrajet" value="${histo.idTrajet}">
                                            <button class="btn btn-primary" type="submit" name="afaire" value="detailHistorique">Details</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </li>
        </ul>
    </c:otherwise>
</c:choose>
