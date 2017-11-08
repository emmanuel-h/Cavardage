<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 27/10/17
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
${message}<br>
<form action="ControleurUtilisateur" method="post">
    <div class="form-group">
        <label for="mdp">Veuillez entrer votre mot de passe</label>
        <input type="password" id="mdp" class="form-control" name="motDePasse">
    </div>
    <div class="form-group">
        <input type="hidden" name="confirmation" value="ok">
        <button type="submit" class="btn btn-primary" name="afaire" value="supprimerCompte">Supprimer compte</button>
    </div>
</form>
