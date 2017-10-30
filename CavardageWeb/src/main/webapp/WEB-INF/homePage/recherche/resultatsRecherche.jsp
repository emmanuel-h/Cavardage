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
<ul class="list-group">
    <c:forEach items="${listeTrajetRecherche}" var="trajet">
        <li class="list-group-item">
            <table class="table table-bordered">
                <tr>
                    <td>${trajet.villeDepart}(${trajet.departementDepart})</td>
                    <td>${trajet.villeArrive}(${trajet.departementArrivee})</td>
                    <td>${trajet.date}</td>
                    <td>${trajet.heure}</td>
                </tr>
            </table>
        </li>
    </c:forEach>
</ul>