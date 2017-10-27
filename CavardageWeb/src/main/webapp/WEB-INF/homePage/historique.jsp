<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="ControleurUtilisateur" class="form-group">
    <ul class="list-group">
        <c:forEach items="${listeHistorique}" var="trajet">
            <li class="list-group-item">
                <table>
                    <tr>
                        <td class="col-lg-3">${trajet.villeDepart}</td>
                        <td class="col-lg-3">${trajet.villeArrivee}</td>
                        <td class="col-lg-3">${trajet.dateDepart}</td>
                        <c:if test="${trajet.role == 'conducteur'}">
                            <td class="col-lg-2">${trajet.nomVehicule}</td>
                        </c:if>
                        <c:if test="${trajet.role == 'passager'}">
                            <td class="col-lg-2">${trajet.nbReservation}</td>
                        </c:if>
                        <td class="col-lg-1">
                            <input type="hidden" name="idTrajet" value="${trajet.idTrajet}">
                            <button class="btn btn-primary" type="submit" name="afaire" value="detailHistorique">Details</button>
                        </td>
                    </tr>
                </table>
            </li>
        </c:forEach>
    </ul>
</form>
