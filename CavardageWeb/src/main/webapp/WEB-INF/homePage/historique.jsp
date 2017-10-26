<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
