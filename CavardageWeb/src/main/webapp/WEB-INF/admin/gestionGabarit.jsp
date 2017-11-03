<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 25/10/17
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="ControleurAdmin">
    <legend>Ajouter un gabarit</legend>
    <div class="form-group">
        <label for="nomGabarit1">Nom du gabarit : </label>
        <input type="text" id="nomGabarit1" name="nomGabaritAAjouter" class="form-control" autocomplete="off" required>
    </div>
    <div class="form-group">
        <button class="btn btn-primary" type="submit" name="boutonAdmin" value="ajouterGabarit">Ajouter un gabarit</button>
    </div>
</form>

<form method="post" action="ControleurAdmin">
    <legend>Supprimer un gabarit</legend>
    <div class="form-group">
        <label>Nom du gabarit : </label>
        <datalist id="nomGabaritList">
            <c:forEach items="${listeGabarits}" var="gabarit">
                <option value="${gabarit}"/>
            </c:forEach>
        </datalist>
        <input type="text" list="nomGabaritList" class="form-control" name="nomGabaritASupprimer" autocomplete="off" required>
    </div>
    <div class="form-group">
        <button class="btn btn-primary" type="submit" name="boutonAdmin" value="supprimerGabarit">Supprimer un gabarit</button>
    </div>
</form>