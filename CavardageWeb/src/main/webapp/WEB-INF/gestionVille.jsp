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
    <legend>Ajouter une ville</legend>
    <div class="form-group">
        <label for="nomVille1">Nom de la ville : </label>
        <input type="text" id="nomVille1" name="nomVilleAAjouter" class="form-control" autocomplete="off" required>
        <label for="departementVille1">Département de la ville : </label>
        <input type="text" id="departementVille1" name="departementVilleAAjouter" class="form-control" autocomplete="off" required>
    </div>
    <div class="form-group">
        <button class="btn btn-primary" type="submit" name="boutonAdmin" value="ajouterVille">Ajouter une ville</button>
    </div>
</form>

<form method="post" action="ControleurAdmin">
    <legend>Supprimer une ville</legend>
    <div class="form-group">
        <label>Nom de la ville : </label>
        <datalist id="nomVilleList">
            <c:forEach items="${listeVilles}" var="ville">
                <option value="${ville.nomVille}(${ville.departement})"/>
            </c:forEach>
        </datalist>
        <input type="text" list="nomVilleList" class="form-control" name="nomVilleASupprimer" autocomplete="off" required>
    </div>
    <div class="form-group">
        <button class="btn btn-primary" type="submit" name="boutonAdmin" value="supprimerVille">Supprimer une ville</button>
    </div>
</form>
