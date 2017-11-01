<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<label>Note moyenne : &nbsp;</label><label>${noteMoyenne}</label><br>
<label>Dernières appréciations reçues : </label><br>
<c:choose>
   <c:when test="${!empty appreciations}">
<li class="list-group-item">
    <table class="table table-bordered">
        <tr>
            <th>Note</th>
            <th>Commentaire</th>
        </tr>
        <c:forEach items="${appreciations}" var="appreciation">
            <tr>
                <td>${appreciation.note}</td>
                <td>${appreciation.commentaire}</td>
            </tr>
        </c:forEach>
    </table>
</li></c:when>
<c:otherwise>
    <label>Vous n'avez pas encore reçu d'appréciations pour le moment</label>
</c:otherwise>
</c:choose>
<br>
<label>Derniers trajets effectués :</label>
<ul class="list-group">
    <li class="list-group-item">
        <form action="ControleurUtilisateur" method="post">
            <table class="table table-bordered">
                <tr>
                    <th class="col-lg-3">Ville de départ</th>
                    <th class="col-lg-3">Ville d'arrivée</th>
                    <th class="col-lg-3">Date</th>
                    <th class="col-lg-3">Heure</th>
                </tr>
                <c:forEach items="${listeTrajetEffectues}" var="trajet">
                    <tr>
                        <td>${trajet.villeDepart}(${trajet.departementDepart})</td>
                        <td>${trajet.villeArrivee}(${trajet.departementArrivee})</td>
                        <td>${trajet.date}</td>
                        <td>${trajet.heure}</td>
                        <td><button type="submit" name="afaire" value="detailsNoterTrajet">Noter</button> </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </li>
</ul>