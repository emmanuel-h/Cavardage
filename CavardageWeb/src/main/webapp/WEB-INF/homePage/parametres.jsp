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