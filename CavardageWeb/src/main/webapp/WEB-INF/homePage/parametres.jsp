<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
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
    <legend>Changer le mot de passe</legend>
    <div class="row">
        <div class="form-group col-lg-6">
            <label for="mdp1">Nouveau mot de passe : </label>
            <input type="password" id="mdp1" class="form-control" name="nouveauMdP1" required>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-lg-6">
            <label for="mdp2">Confirmation : </label>
            <input type="password" id="mdp2" class="form-control" name="nouveauMdP2" required>
        </div>
    </div>
    <div class="form-group">
        <button class="btn btn-primary" type="submit" style="width: 20%;" name="afaire" value="changerMotDePasse">Changer mot de passe</button>
    </div>
</form>

<form method="post" action="ControleurUtilisateur">
    <div class="form-group">
        <button class="btn btn-danger" type="submit" style="width: 20%; margin-top: 25px" name="afaire" value="supprimerCompte">Supprimer compte</button>
    </div>
</form>
<!--
<div class="container col-sm-6 col-sm-offset-2">
    <form action="ControleurUtilisateur" method="post">
        <label class="col-sm-">Nouveau mot de passe</label>
        <input class="form-control" type="password" name="nouveauMdP1" required>
        <label>Retapez le mot de passe</label>
        <input class="form-control" type="password" name="nouveauMdP2" required>
            <button class="form-control btn-primary" type="submit" name="afaire" value="changerMotDePasse" style="width: 50%;margin-top: 5px;float:left">Changer mot de passe</button><br>
            <div class="clearfix"></div>
    </form>
    <form action="ControleurUtilisateur" method="post">
        <button class="form-control btn-danger" type="submit" name="afaire" value="supprimerCompte" style="width: 50%;margin-top: 15px;float:left">Supprimer compte</button>
    </form>
</div>


<div class="clearfix"></div>
<div class="help-block"></div>
-->