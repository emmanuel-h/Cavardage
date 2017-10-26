<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cavardage ~ Historique ~</title>
</head>
<body>

    <b:kickstart title="Cavardage">

    </b:kickstart>

    <form method="post" action="ControleurUtilisateur" class="form-group">
        <ul class="list-group">
            <c:forEach items="${listeHistorique}" var="trajet">
                <li class="list-group-item">
                    <table class="table table-bordered">
                        <tr>
                            <td>${trajet.villeDepart}</td>
                            <td>${trajet.villeArrivee}</td>
                            <td>${trajet.dateDepart}</td>
                            <c:if test="${trajet.role == 'conducteur'}">
                                <td>${trajet.nomVehicule}</td>
                            </c:if>
                            <c:if test="${trajet.role == 'passager'}">
                                <td>${trajet.nbReservation}</td>
                            </c:if>
                            <td>
                                <input type="hidden" name="idTrajet" value="${trajet.idTrajet}">
                                <button class="btn btn-primary" type="submit" name="afaire" value="detailHistorique">Details</button>
                            </td>
                        </tr>
                    </table>
                </li>
            </c:forEach>
        </ul>
    </form>
</body>
</html>
