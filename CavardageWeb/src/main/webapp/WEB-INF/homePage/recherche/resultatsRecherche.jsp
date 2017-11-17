<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 30/10/17
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${!empty villeDepart && !empty villeArrivee && !empty date}">
    <label>Résultat de la recherche: ${villeDepart} à destination de ${villeArrivee}, le ${date} <c:if test="${!empty prix}">pour un prix inférieur à ${prix}€</c:if>  </label>
</c:if>
<c:choose>
    <c:when test="${empty listeTrajetRecherche}">
        <c:choose>
            <c:when test="${empty messageErreur}">
                <div class="alert alert-warning" style="margin-top: 10px">
                        Il n'existe pas de résultat pour votre recherche
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger" style="margin-top: 10px">
                        ${messageErreur}
                </div>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <ul class="list-group">

                <li class="list-group-item">
                    <table class="table table-bordered">
                        <tr>
                            <th class="col-lg-3">Ville de départ</th>
                            <th class="col-lg-3">Ville d'arrivée</th>
                            <th class="col-lg-2">Date</th>
                            <th class="col-lg-1">Heure</th>
                            <th class="col-lg-1">Places restantes</th>
                        </tr>
                        <c:forEach items="${listeTrajetRecherche}" var="trajet">
                        <tr>
                            <td>${trajet.villeDepart}(${trajet.departementDepart})</td>
                            <td>${trajet.villeArrivee}(${trajet.departementArrivee})</td>
                            <td>${trajet.date}</td>
                            <td>${trajet.heure}</td>
                            <td>${trajet.nbPlaces}</td>
                            <c:if test="${!empty pageContext.request.remoteUser}">
                                <c:if test="${trajet.loginConducteur eq pageContext.request.remoteUser}">
                                    <td class="col-lg-1">
                                        <form action="ControleurUtilisateur" method="post">
                                            <input type="hidden" name="loginConducteur" value="${trajet.loginConducteur}">
                                            <input type="hidden" name="idTrajet" value="${trajet.id}">
                                            <button class="btn btn-primary" style="width: 100%" type="submit" name="afaire" value="gererTrajet">Gérer</button>
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${trajet.loginConducteur ne pageContext.request.remoteUser}">
                                    <td class="col-lg-1">
                                        <form action="ControleurUtilisateur" method="post">
                                            <input type="hidden" name="loginConducteur" value="${trajet.loginConducteur}">
                                            <input type="hidden" name="idTrajet" value="${trajet.id}">
                                            <button class="btn btn-primary" style="width: 100%" type="submit" name="afaire" value="detailsTrajet">Détails</button>
                                        </form>
                                    </td>
                                </c:if>
                            </c:if>
                        </tr>
                        </c:forEach>
                    </table>
                </li>
        </ul>
    </c:otherwise>
</c:choose>
