<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 27/10/17
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty message}">
    <div class="alert alert-warning" style="margin-top: 10px">
            ${message}
    </div>
</c:if>
<form method="post" action="ControleurUtilisateur">
    <legend>Suppression du compte</legend>
    <div class="row">
        <div class="form-group col-lg-6">
            <label for="mdp">Veuillez entrer votre mot de passe</label>
            <input type="password" id="mdp" class="form-control" name="motDePasse" required>
        </div>
    </div>
    <div>
        <input type="hidden" name="confirmation" value="ok">
        <button type="submit" class="btn btn-danger" name="afaire" value="supprimerCompte">Supprimer compte</button>
    </div>
</form>
<!--
<div class="container col-sm-6 col-sm-offset-2">
<form action="ControleurUtilisateur" method="post">
    <div class="form-group">
        <label for="mdp">Veuillez entrer votre mot de passe</label>
        <input type="password" id="mdp" class="form-control" name="motDePasse" required>
    </div>
    <div class="form-group">
        <input type="hidden" name="confirmation" value="ok">
        <button type="submit" class="btn btn-danger" name="afaire" value="supprimerCompte">Supprimer compte</button>
    </div>
</form>
</div>
-->