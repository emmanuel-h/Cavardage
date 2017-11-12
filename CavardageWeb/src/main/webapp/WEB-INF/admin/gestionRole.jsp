<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 11/11/17
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${!empty messageErreur}">
    <div class="alert alert-danger">
        <strong>Erreur : </strong>${messageErreur}
    </div>
</c:if>
<c:if test="${!empty messageSucces}">
    <div class="alert alert-success">
        <strong>Succès : </strong>${messageSucces}
    </div>
</c:if>

<form method="post" action="ControleurAdmin">
    <div class="form-group">
        <label for="login">Identifiant : </label>
        <input type="text" class="form-control" id="login" size="25" name="login" autocomplete="off" required>
    </div>
    <div class="form-group">
        <label for="nom">Nom : </label>
        <input type="text" class="form-control" id="nom" size="25" name="nom" autocomplete="off" required>
    </div>
    <div class="form-group">
        <label for="mdp">Mot de passe : </label>
        <input type="password" class="form-control" id="mdp" size="15" name="mdp" autocomplete="off" required>
    </div>
    <div class="form-group">
        <label for="mdpConf">Confirmation : </label>
        <input type="password" class="form-control" id="mdpConf" size="15" name="mdpConf" autocomplete="off" required>
    </div>
    <div class="form-group">
        <button class="btn btn-primary" type="submit" name="boutonAdmin" value="creerCompteAdmin">Créer un compte administrateur</button>
    </div>
</form>