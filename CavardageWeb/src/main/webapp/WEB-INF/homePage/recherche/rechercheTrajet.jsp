<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<datalist id="listeVille">
    <c:forEach items="${listeVilles}" var="ville">
        <option value="${ville.nomVille}(${ville.departement})"/>
    </c:forEach>
</datalist>
<div>
    <h2>Recherche d'un trajet:</h2>
    <div class="row">
        <div class="form-group col-lg-4">
            <label for="villeD">Ville de départ : </label>
            <c:if test="${not empty villeDepart}">
                <input type="text" list="listeVille" id="villeD" class="form-control" name="nomVilleDepart" value="${villeDepart}"
                                                         autocomplete="off" pattern="[A-Z]{1}.{0,}[\(][0-9]{2}[\)]" onblur="villesDifferentes()" required>
            </c:if>
            <c:if test="${empty villeDepart}">
                <input type="text" list="listeVille" id="villeD" class="form-control" name="nomVilleDepart"
                   autocomplete="off" pattern="[A-Z]{1}.{0,}[\(][0-9]{2}[\)]" onblur="villesDifferentes()" required>
            </c:if>
        </div>
        <div class="form-group col-lg-4">
            <label for="villeA">Ville d'arrivée : </label>
            <c:if test="${not empty villeArrivee}">
            <input type="text" list="listeVille" id="villeA" class="form-control" name="nomVilleArrivee" value="${villeArrivee}"
                   autocomplete="off" pattern="[A-Z]{1}.{0,}[\(][0-9]{2}[\)]" onblur="villesDifferentes()" required>
            </c:if>
            <c:if test="${empty villeArrivee}">
                <input type="text" list="listeVille" id="villeA" class="form-control" name="nomVilleArrivee"
                       autocomplete="off" pattern="[A-Z]{1}.{0,}[\(][0-9]{2}[\)]" onblur="villesDifferentes()" required>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-lg-3">
            <label for="date">Date : </label>
            <c:if test="${not empty date}">
            <input type="date" class="form-control" name="date" id="date" value="${date}" placeholder="dd/mm/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" autocomplete="off"/>
            </c:if>
            <c:if test="${empty date}">
                <input type="date" class="form-control" name="date" id="date" placeholder="dd/mm/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" autocomplete="off"/>
            </c:if>
        </div>
        <div class="form-group col-lg-3">
            <label for="prix">Prix : (Optionnel)</label>
            <c:if test="${empty prix}">
            <input type="text" class="form-control" id="prix" name="prix" placeholder="€" pattern="[0-9]{1,3}" autocomplete="off"/>
            </c:if>
            <c:if test="${not empty prix}">
                <input type="text" class="form-control" id="prix" name="prix" value="${prix}" placeholder="€" pattern="[0-9]{1,3}" autocomplete="off"/>
            </c:if>
        </div>
        <div class="form-group col-lg-2">
            <button type="submit" class="btn btn-primary pull-right" style="margin-top: 20px" id="bouton" name="afaire" value="afficherRechercheTrajet">Rechercher</button>
        </div>
    </div>






    <c:if test="${!empty messageErreur}">
        <div class="alert alert-danger">
            <strong>Erreur : </strong> ${messageErreur}
        </div>
    </c:if>
</div>

<script type="text/javascript">
    function villesDifferentes() {

    }
</script>