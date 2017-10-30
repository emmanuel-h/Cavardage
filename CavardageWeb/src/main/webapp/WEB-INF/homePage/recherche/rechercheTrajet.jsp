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
        <label>Ville de départ:</label>
        <datalist id="listeVille">
            <c:forEach items="${listeVilles}" var="ville">
                <option value="${ville.nomVille}(${ville.departement})"/>
            </c:forEach>
        </datalist>
        <input type="text" list="listeVille" class="form-control" name="nomVilleDepart" required>
        <label>Ville d'arrivée:</label>
        <input type="text" list="listeVille" class="form-control" name="nomVilleArrivee" required>
        <label>Date:</label>
        <input type="date" name="date"/>
        <label>Prix: (Optionnel)</label>
        <input type="text" name="prix"/>
        <button type="submit" name="afaire" value="afficherRechercheTrajet">Rechercher</button>
</div>